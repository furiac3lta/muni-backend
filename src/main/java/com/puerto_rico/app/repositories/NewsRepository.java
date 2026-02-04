package com.puerto_rico.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.puerto_rico.app.entities.News;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByActiveTrueOrderByCreatedAtDesc();
}
