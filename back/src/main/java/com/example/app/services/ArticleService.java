package com.example.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.app.context.ArticleRepository;
import com.example.app.model.Article;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public ResponseEntity<Article> createArticle(Article entity) {
        try {
            Article article = new Article(entity.getName(), entity.getCost());
            articleRepository.save(article);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    public ResponseEntity<List<Article>> getList() {
        try {
            return new ResponseEntity<>(articleRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    public ResponseEntity<Article> getInfo(String uuid) {
        try {
            Optional<Article> optional = articleRepository.findByUuid(uuid);
            if (optional.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    public ResponseEntity<Article> updateArticle(String uuid, Article entity) {
        try {
            Optional<Article> optional = articleRepository.findByUuid(uuid);
            if (optional.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            Article article = optional.get();
            article.setCost(entity.getCost());
            article.setName(entity.getName());
            articleRepository.save(article);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    public ResponseEntity<Article> deleteArticle(String uuid) {
        try {
            Optional<Article> optional = articleRepository.findByUuid(uuid);
            if (optional.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            articleRepository.delete(optional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

}