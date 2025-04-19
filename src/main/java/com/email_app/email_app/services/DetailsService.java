package com.email_app.email_app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email_app.email_app.model.Details;
import com.email_app.email_app.repository.DetailsRepository;
import com.email_app.email_app.utility.MailUtility;

@Service
public class DetailsService {

    private final Logger logger = LoggerFactory.getLogger(DetailsService.class);

    @Autowired
    private MailUtility mailutility;

    @Autowired
    private DetailsRepository detailsRepository;

    public void sendHRMail(String email, String name, String companyName, boolean isImmediate) throws Exception {
        logger.info("Gathering data...");

        Details details = new Details();
        details.setName(name);
        details.setEmail(email);
        details.setCompanyName(companyName);

        if (isImmediate) {
            logger.info("Immediate joiner found");
            mailutility.sendMail(email, name, "Application for Java developer - Immediate Joiner", companyName);
            details.setImmediate(true);
        } else {
            mailutility.sendMail(email, name, "Application for Java developer", companyName);
            details.setImmediate(false);
        }

        logger.info("Saving details in database");
        detailsRepository.save(details);

    }

    public List<Details> getAllDetails() {
        return detailsRepository.findAll();
    }

    public void deleteDetail(Long id) {
        detailsRepository.deleteById(id);
    }

}
