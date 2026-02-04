package com.puerto_rico.app.repositories;

import com.puerto_rico.app.entities.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
    List<EmergencyContact> findByActiveTrueOrderByPriorityAsc();
}
