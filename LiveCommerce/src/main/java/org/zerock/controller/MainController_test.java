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

    private final ShoppingCartService shoppingCartService;

    private final EventService eventService;

    private final LiveStreamService liveStreamService;

	//로그인
    @GetMapping("/login")
    public String goToLoginPage() {
        return "loginPage";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        // 로그인 로직을 처리하는 코드
        boolean isLoggedIn = userService.loginUser(username, password);
        if (isLoggedIn) {
            return "redirect:/board/main";
        } else {
            return "redirect:/board/login?error";
        }
    }

    @GetMapping("/cart")
    public String goToShoppingCartPage(Model model) {
        // 장바구니 페이지로 이동하는 로직
        List<CartItem> cartItems = shoppingCartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        return "shoppingCartPage";
    }

    @GetMapping("/main")
    public String goToMainPage(Model model) {
        // 메인 페이지로 이동하는 로직
        List<Event> events = eventService.getEvents();
        model.addAttribute("events", events);
        return "mainPage";
    }

    @GetMapping("/event")
    public String goToEventPage(Model model) {
        // 이벤트 페이지로 이동하는 로직
        List<Event> events = eventService.getEvents();
        model.addAttribute("events", events);
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
    public String showCategoryList(Model model) {
        // 카테고리 목록을 보여주는 로직
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "categoryList";
    }
}
