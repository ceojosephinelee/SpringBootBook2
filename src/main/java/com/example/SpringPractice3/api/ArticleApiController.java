package com.example.SpringPractice3.api;

import com.example.SpringPractice3.dto.ArticleForm;
import com.example.SpringPractice3.entity.Article;
import com.example.SpringPractice3.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }



//    @Autowired
//    private ArticleRepository articleRepository;
//
//    @GetMapping("/api/articles") //전체 조회
//    public List<Article> index(){
//        return articleRepository.findAll();
//    }
//
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id){
//        return articleRepository.findById(id).orElse(null);
//    }
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm form){//@RequestBody를 붙여야 JSON 형태로 보내줄 수 있음
        Article created = articleService.create(form);
        return (created!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm form){
        Article updated =articleService.update(id, form);
        return (updated!=null)?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted!=null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> forms){
        List<Article> createdList = articleService.createArticles(forms);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}
