package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.UserVO;
import org.zerock.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("user") UserVO user) {
        return "/user/registerForm";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("user") UserVO user) {
        userService.registerUser(user);
        return "redirect:/customLogin"; // 회원가입 성공 시 로그인 페이지로 이동
    }
}