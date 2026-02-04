package com.puerto_rico.app.services;

import com.puerto_rico.app.services.AuditService;
import com.puerto_rico.app.core.ResourceNotFoundException;
import com.puerto_rico.app.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import com.puerto_rico.app.entities.News;
import com.puerto_rico.app.repositories.NewsRepository;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final AuditService auditService;

    public NewsService(NewsRepository newsRepository, AuditService auditService) {
        this.newsRepository = newsRepository;
        this.auditService = auditService;
    }

    public List<News> findActive() {
        return newsRepository.findByActiveTrueOrderByCreatedAtDesc();
    }

    public News create(News request) {
        News news = new News();
        news.setTitle(request.getTitle());
        news.setContent(request.getContent());
        news.setActive(request.isActive());

        News saved = newsRepository.save(news);
        auditService.log("News", "CREATE", SecurityUtils.currentUsername());
        return saved;
    }

    public News update(Long id, News request) {
        News news = newsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Noticia no encontrada"));

        news.setTitle(request.getTitle());
        news.setContent(request.getContent());
        news.setActive(request.isActive());

        News saved = newsRepository.save(news);
        auditService.log("News", "UPDATE", SecurityUtils.currentUsername());
        return saved;
    }

    public News deactivate(Long id) {
        News news = newsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Noticia no encontrada"));
        news.setActive(false);

        News saved = newsRepository.save(news);
        auditService.log("News", "DELETE", SecurityUtils.currentUsername());
        return saved;
    }
}
