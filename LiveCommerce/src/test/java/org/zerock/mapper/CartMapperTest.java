package org.zerock.mapper;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CartVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CartMapperTest {

    @Autowired
    private CartMapper mapper;

    @Test
    public void testGetAllCarts() {
        List<CartVO> carts = mapper.getAllCarts();
        assertNotNull(carts);
        carts.forEach(cart -> log.info(cart));
    }

    @Test
    public void testGetCart() {
        int cartID = 1; // 유효한 카트 ID 입력
        CartVO cart = mapper.getCart(cartID);
        assertNotNull(cart);
        log.info("장바구니 정보 조회: " + cart);
    }

    @Test
    public void testInsertCart() {
        CartVO newCart = CartVO.builder()
                .userID(1) // 사용자 ID 입력
                .productID(1) // 제품 ID 입력
                .quantity(1) // 수량 입력
                .build();
        mapper.insertCart(newCart);
        assertNotNull(newCart.getCartID());
        log.info("새로운 장바구니 정보 추가: " + newCart);
    }

    @Test
    public void testUpdateCart() {
        int cartID = 1; // 수정할 장바구니 ID 입력
        CartVO updatedCart = mapper.getCart(cartID);
        updatedCart.setQuantity(2); // 수정할 내용 입력
        mapper.updateCart(updatedCart);
        log.info("장바구니 정보 수정: " + updatedCart);
    }

    @Test
    public void testDeleteCart() {
        int cartID = 1; // 삭제할 장바구니 ID 입력
        mapper.deleteCart(cartID);
        log.info("장바구니 정보 삭제: CartID=" + cartID);
    }
}
