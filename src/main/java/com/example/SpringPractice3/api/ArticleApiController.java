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
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm form){
//        Article article = form.toEntity();
//        log.info("id+{}, article:{}", id, article.toString());
//        Article target = articleRepository.findById(id).orElse(null);
//        if(target == null || id != article.getId()){
//            log.info("잘못된 요청! id+{}, article:{}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//        }
//        target.patch(article);
//        Article updated = articleRepository.save(target);
//
//
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//        Article target = articleRepository.findById(id).orElse(null);
//        if(target == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//        }
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
