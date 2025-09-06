package com.example.SpringPractice3.dto;

import com.example.SpringPractice3.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberDto {

    private Long id;
    private String email;
    private String password;

    public Member toEntity() {
        return new Member(id, email, password);
    }
}
