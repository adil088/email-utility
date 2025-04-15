package com.email_app.email_app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.email_app.email_app.services.DetailsService;

@RestController
@RequestMapping("email-app")
public class DetailsController {

    private final Logger logger = LoggerFactory.getLogger(DetailsController.class);

    @Autowired
    private DetailsService detailsService;

    @PutMapping("/send")
    public ResponseEntity<String> sendMail(@RequestParam String email, @RequestParam String name,
            @RequestParam String companyName, @RequestParam String isImmediate) {

        boolean isImmediateBool = Boolean.parseBoolean(isImmediate);

        try {
            logger.info("trying to send email to HR");
            detailsService.sendHRMail(email, name, companyName, isImmediateBool);
            return new ResponseEntity<>("Mail sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("FAILED");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
