package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/customLogin")
    public String customLogin() {
        return "customLogin"; // customLogin.jsp 또는 customLogin.html과 같은 뷰 파일을 반환합니다.
    }
}