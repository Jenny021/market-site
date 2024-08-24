package com.example.app.context;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.Article;

import java.util.*;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    Optional<Article> findByUuid(String uuid);
}