Cafe Online — Spring Boot backend for product catalog and user cart management.
Built REST API (POST /cart/addToCart) to accept customerId plus a list of products and quantities.
Implemented JPA entities and repositories with transactional upsert logic to persist per-customer cart rows.
Computed per-item and aggregate totals and returned consolidated cart DTO responses.
Documented APIs with springdoc OpenAPI/Swagger and configured Spring Security to whitelist docs.
Tech: Java 17, Spring Boot, Spring Data JPA (Hibernate), MySQL, Maven, Lombok.
