package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.CartProductVO;
import org.zerock.domain.CartVO;

public interface CartMapper {

    List<CartVO> getAllCarts();

    CartVO getCart(int cartID);

    void insertCart(CartVO cart);

    void updateCart(CartVO cart);

    void deleteCart(int cartID);

    // 사용자의 장바구니 정보 가져오기
    List<CartVO> getCartByUserID(int userID);
    
    List<CartProductVO> selectCartDetails(@Param("userID") int userID);
    
    void deleteProductFromCart(CartProductVO cartProduct);
}
