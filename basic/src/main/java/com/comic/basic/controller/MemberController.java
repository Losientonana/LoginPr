package com.comic.basic.controller;

import com.comic.basic.entity.MemberEntity;
import com.comic.basic.member.Member;
import com.comic.basic.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }

//    @PostMapping("/member/save")
//    public String save(String memberEmail, String memberPassword, String memberName, Model model){
//      log.info("memberEmail={},memberPassword={},memberName={}",memberEmail,memberPassword,memberName);
//        return null;
//  }

    @PostMapping("/member/save")
    public String save(@ModelAttribute Member member,Model model){
        model.addAttribute("member", member);
//        log.info("1 = {}, 2 ={}, 3={}",member.getMemberName(),member.getMemberEmail(),member.getMemberPassword());
        memberService.save(member);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }


    @PostMapping("/member/login")
    public String login(@ModelAttribute Member member, HttpSession httpSession){
        Member loginResult = memberService.login(member);
        if(loginResult != null) {
            httpSession.setAttribute("loginEmail",loginResult.getMemberEmail());
            //login 성공
            return "main";
        } else {
            return "login";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model){
        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList",memberList);
        return "list";
    }

    @GetMapping("/member/{id}")
    public String getMember(@PathVariable Long id,Model model){
        Member member = memberService.findById(id);
        model.addAttribute("member",member);
        return "detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session,Model model){
        String myEmail = (String) session.getAttribute("loginEmail");
        Member member = memberService.updateForm(myEmail);
        model.addAttribute("updateMember",member);
        return "update";
    }


    @PostMapping("/member/update")
    public String update(@ModelAttribute Member member){
        memberService.update(member);
        return "redirect:/member/" + member.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "index";
    }

    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
//        if (checkResult != null) {
//            return "ok";
//        } else {
//            return "no";
//        }
    }

}