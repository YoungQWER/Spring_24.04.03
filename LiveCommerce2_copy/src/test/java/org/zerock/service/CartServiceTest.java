package org.zerock.service;

import static org.junit.Assert.*;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CartVO;
import java.util.List;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j

public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Test
    public void testGetAllCarts() {
        List<CartVO> carts = cartService.getAllCarts();
        carts.forEach(cart -> log.info(carts));
    }

    @Test
    public void testGetCart() {
        int cartID = 1; // 가져올 카트의 ID를 설정하세요.
        CartVO cart = cartService.getCart(cartID);
        log.info(cart);
    }

    @Test
    public void testInsertCart() {
        CartVO cart = CartVO.builder()
                .userID(3) // 사용자 ID 입력
                .productID(1) // 제품 ID 입력
                .quantity(2) // 수량 입력
                .build();

        cartService.insertCart(cart);
        log.info(cart);
    }

    @Test
    public void testUpdateCart() {
        int cartID = 2; // 업데이트할 카트의 ID를 설정하세요.
        CartVO cart = cartService.getCart(cartID);

        cart.setQuantity(3); // 변경할 수량 입력
         

        cartService.updateCart(cart);
        log.info(cart);
    }

    @Test
    public void testDeleteCart() {
        int cartID = 1; // 삭제할 카트의 ID를 설정하세요.
        cartService.deleteCart(cartID);
        
    }
}