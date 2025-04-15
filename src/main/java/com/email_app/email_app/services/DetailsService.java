package com.email_app.email_app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email_app.email_app.utility.MailUtility;

@Service
public class DetailsService {

    private final Logger logger = LoggerFactory.getLogger(DetailsService.class);

    @Autowired
    private MailUtility mailutility;

    // @Autowired
    // private DetailsRepository detailsRepository;

    public void sendHRMail(String email, String name, String companyName, boolean isImmediate) throws Exception {
        logger.info("Gathering data...");

        if (isImmediate) {
            logger.info("Immediate joiner found");
            mailutility.sendMail(email, name, "Application for Java developer - Immediate Joiner", companyName);
        } else {
            mailutility.sendMail(email, name, "Application for Java developer", companyName);
        }

    }

}
