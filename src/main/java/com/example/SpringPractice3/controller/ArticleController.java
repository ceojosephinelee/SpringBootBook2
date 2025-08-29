package com.example.SpringPractice3.controller;

import com.example.SpringPractice3.entity.Article;
import com.example.SpringPractice3.dto.ArticleForm;
import com.example.SpringPractice3.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j //로깅 기능
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository; //new 생성해줄 필요 없이 선언만 해도 됨.-->springboot의 특성(DI, IOC)

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());
        //DTO를 Entity로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());
        //article 엔티티를 저장해 saved 객체에 반환
        Article saved = articleRepository.save(article); //엔티티를 repository를 통해 db에 저장
        System.out.println(saved.toString());
        return "";
    }
}
