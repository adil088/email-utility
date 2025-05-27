package com.email_app.email_app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            mailutility.sendMail(email, name, "Application for Java developer || Immediate Joiner", companyName);
            details.setImmediate(true);
        } else {
            mailutility.sendMail(email, name, "Application for Java developer || Serving Notice Period", companyName);
            details.setImmediate(false);
        }

        logger.info("Saving details in database");
        detailsRepository.save(details);

    }

//    public List<Details> getAllDetails() {
//        return detailsRepository.findAllByOrderByIdDesc();
//    }

    public Page<Details> getAllDetails(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return detailsRepository.findAll(pageable);
    }

    public void deleteDetail(Long id) {
        detailsRepository.deleteById(id);
    }

    public Details getEmailById(Long id){
        return detailsRepository.findById(id).orElseThrow(()->new RuntimeException("Details not found!"));
    }

}
