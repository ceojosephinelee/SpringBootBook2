package com.example.SpringPractice3.service;

import com.example.SpringPractice3.dto.CommentDto;
import com.example.SpringPractice3.entity.Comment;
import com.example.SpringPractice3.repository.ArticleRepository;
import com.example.SpringPractice3.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    //댓글 조회
    public List<CommentDto> comments(Long articleId) {
        //1.댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        //2. 엔티티->DTO 변환
        List<CommentDto> dtos = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);

        }
        //3. 결과 반환
        return dtos;
    }
    //댓글 생성
    //댓글 수정
    //댓글 삭제
}
