package com.puerto_rico.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.puerto_rico.app.entities.TouristPlace;

public interface TouristPlaceRepository extends JpaRepository<TouristPlace, Long> {
    List<TouristPlace> findByActiveTrue();
}
