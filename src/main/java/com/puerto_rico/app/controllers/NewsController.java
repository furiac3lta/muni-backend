package com.puerto_rico.app.controllers;

import com.puerto_rico.app.core.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.puerto_rico.app.entities.News;
import com.puerto_rico.app.services.NewsService;

@RestController
@RequestMapping
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/api/news")
    public ResponseEntity<ApiResponse<List<News>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok("Noticias", newsService.findActive()));
    }

    @PostMapping("/api/admin/news")
    public ResponseEntity<ApiResponse<News>> create(@RequestBody News request) {
        return ResponseEntity.ok(ApiResponse.ok("Noticia creada", newsService.create(request)));
    }

    @PutMapping("/api/admin/news/{id}")
    public ResponseEntity<ApiResponse<News>> update(@PathVariable Long id, @RequestBody News request) {
        return ResponseEntity.ok(ApiResponse.ok("Noticia actualizada", newsService.update(id, request)));
    }

    @DeleteMapping("/api/admin/news/{id}")
    public ResponseEntity<ApiResponse<News>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Noticia desactivada", newsService.deactivate(id)));
    }
}
