package com.example.SpringPractice3.controller;

import com.example.SpringPractice3.dto.CommentDto;
import com.example.SpringPractice3.entity.Article;
import com.example.SpringPractice3.dto.ArticleForm;
import com.example.SpringPractice3.repository.ArticleRepository;
import com.example.SpringPractice3.service.CommentService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j //로깅 기능
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository; //new 생성해줄 필요 없이 선언만 해도 됨.-->springboot의 특성(DI, IOC)
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());// 로깅 코드 추가
        //System.out.println(form.toString()); //실제 서버에서 println 쓰면 안됨! 기록에 남지 않고 서버 성능에도 악영향을 끼침.
        //DTO를 Entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());
        //article 엔티티를 저장해 saved 객체에 반환
        Article saved = articleRepository.save(article); //엔티티를 repository를 통해 db에 저장
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){ //매개변수로 id 받아오기
        log.info("id: " + id);//id를 잘 받았는지 확인하는 로그 찍기
        //1. id를 조회해 데이터 가져오기
        //방법1. Optional<Article> articleEntity = articleRepository.findById(id);
        //방법2.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        //2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentsDtos);
        //3. 뷰 페이지 반환하기

        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하가
        model.addAttribute("article", articleEntity);

        return "articles/edit";

    }
    @PostMapping("/articles/update")
    public String updateArticle(ArticleForm form){
        log.info(form.toString());
        //1.DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2. 엔티티를 DB에 저장하기
        //2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        log.info(target.toString());
        //2-2. 기존 데이터 값 갱신하기
        if(target != null){
            articleRepository.save(articleEntity);
        }
        //3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/"+articleEntity.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!");
        //1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 엔티티 삭제하기
        if(target != null){ //삭제할 대상이 있는지 확인
            articleRepository.delete(target); //db에서 삭제
            rttr.addFlashAttribute("msg", "삭제되었습니다!");
        }
        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }

}
