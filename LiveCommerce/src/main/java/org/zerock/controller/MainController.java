package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.CartVO;
import org.zerock.domain.EventVO;
import org.zerock.service.EventService;
import org.zerock.service.LiveStreamService;
import org.zerock.service.ShoppingCartService;
import org.zerock.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class MainController {

	private final UserService userService;
	
	private final ShoppingCartService shppingCartService;
	
	private final EventService eventService;
	
	private final LiveStreamService liveStreamService; 
	
	//로그인
	@GetMapping("/login")
    public String goToLoginPage() {
        return "loginPage";
    }
 
 	//로그인 받아주기
 	@PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {

	 	boolean isLoggedIn = userService.loginUser(username, password);
        if (isLoggedIn) {
            return "redirect:/board/main";
        } else {
            return "redirect:/board/login?error";
        }
    }

 	//장바구니 이동
 	@GetMapping("/cart")
    public String goToShoppingCartPage(Model model) {

	 List<CartVO> cartItems = shppingCartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        return "shoppingCartPage";
    }

 	//메인페이지 새로고침
 	@GetMapping("/main")
 	public String goToMainPage() {
 	    return "mainPage";
 	}

    @GetMapping("/event")
    public String goToEventPage() {
        return "eventPage";
    }

    @GetMapping("/live")
    public String goToLiveStreamPage(Model model) {
        // 라이브 페이지로 이동하는 로직
        List<LiveStream> liveStreams = liveStreamService.getLiveStreams();
        model.addAttribute("liveStreams", liveStreams);
        return "liveStreamPage";
    }

    @GetMapping("/category")
    public String showCategoryList() {
        return "categoryList";
    }


	
	
}
