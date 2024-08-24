package com.example.app.controller;

import com.example.app.model.Article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.app.services.ArticleService;


@RestController
@CrossOrigin
@RequestMapping("/api/article")
class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<Article> createArticle(@RequestBody Article entity) {
        return articleService.createArticle(entity);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Article>> getArticleList() {
        return articleService.getList();
    }

    @GetMapping("/info/{uuid}")
    public ResponseEntity<Article> getArticleInfo(@PathVariable String uuid) {
        return articleService.getInfo(uuid);
    }

    @PatchMapping("/update/{uuid}")
    public ResponseEntity<Article> updateArticle(@PathVariable String uuid, @RequestBody Article entity) {
        return articleService.updateArticle(uuid, entity);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Article> deleteArticle(@PathVariable String uuid) {
        return articleService.deleteArticle(uuid);
    }
}