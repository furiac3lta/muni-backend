package com.puerto_rico.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.puerto_rico.app.entities.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByActiveTrueOrderByCreatedAtDesc();
}
