package com.example.SpringPractice3.api;

import com.example.SpringPractice3.dto.ArticleForm;
import com.example.SpringPractice3.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;
    @Test
    @Transactional
    void create_성공() {
        String title = "라라라";
        String content = "444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        Article article = articleService.create(dto);

        assertEquals(expected.toString(),article.toString());

    }

    @Test
    void index(){
        //1. 예상 데이터
        Article a = new Article(1L,"가가가","111");
        Article b = new Article(2L,"나나나","222");
        Article c = new Article(3L,"다다다","333");
        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));//a,b,c 합치기
//2. 실제 데이터
        List<Article> articles = articleService.index();
        //3. 비교 및 검증
        assertEquals(expected.toString(),articles.toString());
    }


    @Test
    void create_실패() {
        Long id = 4L;
        String title = "라라라";
        String content = "444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        Article article = articleService.create(dto);
        assertEquals(expected,article);

    }
    @Test
    void show_실패() {
        Long id = -1L;
        Article expected = null;
        Article article=articleService.show(id);;
        assertEquals(expected,article);

    }
    @Test
    void show_성공() {
        Long id = 1L;
        Article expected = new Article(1L, "가가가", "111");

        Article article=articleService.show(id);;
        assertEquals(expected.toString(),article.toString());

    }

}