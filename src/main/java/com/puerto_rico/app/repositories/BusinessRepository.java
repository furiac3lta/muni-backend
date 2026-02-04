package com.puerto_rico.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.puerto_rico.app.entities.Business;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    List<Business> findByActiveTrue();
}
