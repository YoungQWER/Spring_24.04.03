package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.CartVO;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Override
	public List<CartVO> getCartItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToCart(CartVO cartItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromCart(int itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkout() {
		// TODO Auto-generated method stub
		
	}

}
