package org.zerock.service;

import java.util.List;

import org.zerock.domain.CartVO;

public interface ShoppingCartService {
	
	List<CartVO> getCartItems();
    void addToCart(CartVO cartItem);
    void removeFromCart(int itemId);
    void checkout();
}
