package org.zerock.service;

import java.util.List;

import org.zerock.domain.CartProductVO;
import org.zerock.domain.CartVO;

public interface CartService {

    List<CartVO> getAllCarts();

    CartVO getCart(int cartID);

    void insertCart(CartVO cart);

    void updateCart(CartVO cart);

    void deleteCart(int cartID);

	List<CartVO> getCartByUserID(int userID);
	
    List<CartProductVO> getCartDetails(int userID);
}