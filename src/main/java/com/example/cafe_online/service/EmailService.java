package com.example.cafe_online.service;


import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String customerName) {

        /*SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject("Thank You For Visiting Nova Cafe");

        *//*message.setText(
                "Heyy ❤" + customerName +
                        ", Thanks for visiting Nova Cafe ☕"
        );*//*
        message.setText(
                "Heyy ❤" + customerName +
                        ", This message is just a small reminder that someone " +
                        "is thinking about you right now ✨\uD83E\uDEE3" +
                        "<img src='cid:cafeImage'>"
        );
        mailSender.send(message);
    }*/
        try {

            MimeMessage message =
                    mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setTo(toEmail);

            helper.setSubject("Here is a gentle Warning");
            helper.setFrom("unknown@gmail.com", "Nova Cafe");
            String htmlContent =
                    "<h2>Heyy ❤ " + customerName + "</h2>"
                            + "<p>This message is just a small reminder "
                            + "that someone is thinking about you right now ✨🥹</p>"
                            + "<p>Grab a coffee and enjoy your day ☕</p>"
                            + "<img src='cid:cafeImage' width='300'/>";

            helper.setText(htmlContent, true);

            ClassPathResource image =
                    new ClassPathResource("static/testing.jpg");

            helper.addInline("cafeImage", image);

            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}