package com.example.cafe_online.Util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class jwtUtil {

    private static final Logger log = LoggerFactory.getLogger(jwtUtil.class);

    private final SecretKey key;
    private final long expirationMs;
    private final String issuer;

    // inject via application.properties
    public jwtUtil(
            @Value("${jwt.secret:}") String secretBase64OrRaw,
            @Value("${jwt.expiration-ms:86400000}") long expirationMs,
            @Value("${jwt.issuer:cafe_online}") String issuer
    ) {
        this.expirationMs = expirationMs;
        this.issuer = issuer;

        byte[] keyBytes = null;

        if (secretBase64OrRaw != null && !secretBase64OrRaw.isBlank()) {
            try {
                // Try Base64 first
                keyBytes = Decoders.BASE64.decode(secretBase64OrRaw);
            } catch (Exception e) {
                // If not Base64, use the raw UTF-8 bytes
                keyBytes = secretBase64OrRaw.getBytes(StandardCharsets.UTF_8);
            }

            if (keyBytes.length >= 32) {
                this.key = Keys.hmacShaKeyFor(keyBytes);
                return;
            }

            // Provided secret too short - log and fall through to generate a secure key for dev
            log.warn("Configured JWT secret is too short ({} bytes). A 32+ byte key is required for HS256. " +
                    "Falling back to a generated key for development. Set a proper jwt.secret for production.", keyBytes.length);
        } else {
            log.warn("No jwt.secret configured. Generating an ephemeral key for development. " +
                    "Configure jwt.secret or set environment variable JWT_SECRET for persistent tokens in other environments.");
        }

        // Generate a secure random key for HS256 so the application can start in local/dev
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateToken(String subject, Map<String, Object> extraClaims) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(extraClaims != null ? extraClaims : Map.of())
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(expirationMs)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateTokenForUsername(String username) {
        return generateToken(username, null);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            // token invalid, expired, unsupported, etc.
            throw e;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(Date.from(Instant.now()));
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (JwtException e) {
            return false;
        }
    }

    // convenience: validate without UserDetails — checks signature and not expired
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return !isTokenExpired(token);
        } catch (JwtException e) {
            return false;
        }
    }
}