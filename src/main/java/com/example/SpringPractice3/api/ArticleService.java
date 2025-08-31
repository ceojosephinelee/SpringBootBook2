package com.example.SpringPractice3.api;

import com.example.SpringPractice3.dto.ArticleForm;
import com.example.SpringPractice3.entity.Article;
import com.example.SpringPractice3.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }


    public Article create(ArticleForm form) {
        Article article = form.toEntity();
        return articleRepository.save(article);
    }
}
