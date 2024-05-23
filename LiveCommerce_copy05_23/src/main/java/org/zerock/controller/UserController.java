package org.zerock.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import lombok.extern.log4j.Log4j;



@Controller
@RequestMapping("/user")
@Log4j
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
    
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute("user") UserVO user, BindingResult result) {
    	
    	// 현재 로그인한 사용자의 정보 가져오기
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	
        // 사용자 정보 가져오기
        UserVO currentUser = userService.selectUserByUserName(username);

    	if (result.hasErrors()) {
            return "/error"; // 에러 발생 시 처리할 페이지로 리다이렉트
        }
        
        // 가져온 사용자 정보에 업데이트할 정보 적용
        currentUser.setEmail(user.getEmail());
        currentUser.setShippingAddress(user.getShippingAddress());
        currentUser.setShippingPostalCode(user.getShippingPostalCode());
        
        // 사용자 정보 업데이트
        userService.updateUser(currentUser);
        
        log.info("++++++++++++++++user : "+ currentUser);
        
        return "redirect:/live/profile"; // 프로필 페이지로 리다이렉트
    }

}