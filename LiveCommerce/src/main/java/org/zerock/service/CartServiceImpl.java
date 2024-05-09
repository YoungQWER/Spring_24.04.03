package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.CartVO;
import org.zerock.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartMapper cartMapper;

    @Override
    public List<CartVO> getAllCarts() {
        return cartMapper.getAllCarts();
    }

    @Override
    public CartVO getCart(int cartID) {
        return cartMapper.getCart(cartID);
    }

    @Override
    public void insertCart(CartVO cart) {
        cartMapper.insertCart(cart);
        // 여기서 다른 비즈니스 로직 처리 가능
    }

    @Override 
    public void updateCart(CartVO cart) {
        cartMapper.updateCart(cart);
        // 여기서 다른 비즈니스 로직 처리 가능
    }

    @Override
    public void deleteCart(int cartID) {
        cartMapper.deleteCart(cartID);
        // 여기서 다른 비즈니스 로직 처리 가능
    }
}