package com.example.SpringPractice3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article; //외래키
    @Column
    private String nickname;
    @Column
    private String body;

}
