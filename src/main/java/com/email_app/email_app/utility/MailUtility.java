package com.email_app.email_app.utility;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;

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
        File file = new File("src/main/resources/static/Mohd_Adil_Resume_.pdf"); // replace with your file path
        if (file.exists()) {
            logger.info("Uploading resume....");
            mimeMessageHelper.addAttachment(file.getName(), file);
        } else {
            logger.error("File not found at the specified location");
            logger.info("Uploading failed....");
        }
        javaMailSender.send(mimeMessage);
        logger.info("Email sent");

    }

}
