package com.example.SpringPractice3.controller;

import com.example.SpringPractice3.dto.MemberDto;
import com.example.SpringPractice3.entity.Member;
import com.example.SpringPractice3.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signup() {
        return "members/new";
    }

    @PostMapping("/members/create")
    public String createMember(MemberDto dto) {
        Member member = dto.toEntity();
        log.info(member.toString());
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/"+saved.getId();
    }
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "members/show";
    }
    @GetMapping("/members")
    public String showAll(Model model) {
        ArrayList<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList", memberList);
        return "members/all";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);

        return "members/edit";
    }

    @PostMapping("/members/update")
    public String updateMember(MemberDto dto) {
        Member memberEntity = dto.toEntity();
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if(target!=null) {
            memberRepository.save(memberEntity);

        }

        return "redirect:/members/"+memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rtts) {
        Member memberEntity = memberRepository.findById(id).orElse(null);
        if(memberEntity!=null) {
            memberRepository.delete(memberEntity);
            rtts.addFlashAttribute("message", "Member deleted successfully");
        }
        return "redirect:/members";
    }
}
