package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.zerock.domain.UserVO;
import org.zerock.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {
   
   @Autowired
    private UserService userService;
   
   @GetMapping("/accessError")
   public void accessDenied(Authentication auth, Model model) {
      
      log.info("-------------------------------------");
      log.info(auth);
      
      model.addAttribute("msg", "접근 권한이 없습니다.");
   }
   
   @GetMapping("/customLogin")
   public void loginInput(String error, String logout, Model model) {
      
      log.info("error : " + error);
      log.info("logout : " + logout);
      
      if(error != null) {
         model.addAttribute("error", "아이디 or 비번이 틀립니다.");
      }

      
      if(logout != null) {
         model.addAttribute("logout", "로그아웃!!!!");
      }
      
   }
   
     @GetMapping("/customLogout")
   public void logoutGET() {
      log.info("custom logout.........");
   }
    
    
}

