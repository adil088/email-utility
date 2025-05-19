package com.email_app.email_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.email_app.email_app.model.Details;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface DetailsRepository extends JpaRepository<Details, Long> {

//    List<Details> findAllByOrderByIdDesc();
//    Page<Details> findAllByOrderByIdDesc(Pageable pageable);
    Page<Details> findAll(Pageable pageable);
}
