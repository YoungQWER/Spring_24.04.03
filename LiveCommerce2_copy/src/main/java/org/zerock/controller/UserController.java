package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.UserVO;
import org.zerock.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new UserVO());
        return "/user/registerForm"; // 회원가입 폼으로 이동
        
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute UserVO user) {
        userService.registerUser(user);
        return "redirect:/user/login"; // 회원가입 성공 시 로그인 페이지로 이동
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/user/loginForm"; // 로그인 폼으로 이동
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute UserVO user, Model model) {
        boolean loginResult = userService.loginUser(user.getUsername(), user.getPassword());
        if (loginResult) {
            // 로그인 성공 시 처리 (예: 세션에 사용자 정보 저장 등)
            return "redirect:/live/main"; // 로그인 성공 시 메인 페이지로 이동
        } else {
            model.addAttribute("error", "로그인 실패"); // 로그인 실패 시 에러 메시지 전달
            return "/user/loginForm"; // 로그인 폼으로 다시 이동
        }
    }
}