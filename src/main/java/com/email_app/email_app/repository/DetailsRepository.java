package com.email_app.email_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.email_app.email_app.model.Details;

public interface DetailsRepository extends JpaRepository<Details, Long> {

}
