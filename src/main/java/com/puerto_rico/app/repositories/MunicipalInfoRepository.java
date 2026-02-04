package com.puerto_rico.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.puerto_rico.app.entities.MunicipalInfo;

public interface MunicipalInfoRepository extends JpaRepository<MunicipalInfo, Long> {
    Optional<MunicipalInfo> findTopByOrderByUpdatedAtDesc();
}
