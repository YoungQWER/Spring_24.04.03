package org.zerock.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private final PasswordEncoder passwordEncoder; // PasswordEncoder 주입

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("user") UserVO user) {
        return "/user/registerForm";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("user")  UserVO user, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/registerForm";
        }
        
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.registerUser(user);
        return "redirect:/customLogin"; // 회원가입 성공 시 로그인 페이지로 이동
    }
}