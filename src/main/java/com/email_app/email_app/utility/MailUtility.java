package com.email_app.email_app.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.activation.DataSource;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class MailUtility {

    private final Logger logger = LoggerFactory.getLogger(MailUtility.class);

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    public void sendMail(
            String email,
            String name,
            String subject,
            String companyName) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        logger.info("Started context");
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("companyName", companyName);
        logger.info("Creating context...");
        logger.info("Variable set..");
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setTo(email);
        String htmlEmail = templateEngine.process("emailTemp", context);
        mimeMessageHelper.setText(htmlEmail, true);

        // Attach a file from a specific location
        ClassPathResource file = new ClassPathResource("static/Mohd_Adil_Resume_.pdf");
        DataSource source = new ByteArrayDataSource(file.getInputStream(), "application/pdf");
        if (file.exists()) {
            logger.info("Uploading resume....");
            mimeMessageHelper.addAttachment(file.getFilename(), source);
        } else {
            logger.error("File not found at the specified location");
            logger.info("Uploading failed....");
        }
        javaMailSender.send(mimeMessage);
        logger.info("Email sent");

    }

}
