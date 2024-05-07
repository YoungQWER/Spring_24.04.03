package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CartVO;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

public class CartMapperTest {
    
    @Autowired
    private CartMapper cart;

    @Test //C
    public void testC() {
        CartVO c = CartVO.builder()
                .userID(1)
                .productID(5)
                .quantity(10)
                .build();
        cart.addToCart(c);
       
    }
}