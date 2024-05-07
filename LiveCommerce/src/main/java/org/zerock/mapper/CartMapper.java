package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.CartVO;

public interface CartMapper {

    void addToCart(CartVO cart);

    CartVO getCartItem(@Param("cartID") int cartID);

    void updateCartItem(CartVO cart);

    void deleteCartItem(@Param("cartID") int cartID);
}