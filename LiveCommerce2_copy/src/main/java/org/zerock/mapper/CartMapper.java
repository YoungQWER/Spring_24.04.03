package org.zerock.mapper;

import java.util.List;
import org.zerock.domain.CartVO;

public interface CartMapper {

    List<CartVO> getAllCarts();

    CartVO getCart(int cartID);

    void insertCart(CartVO cart);

    void updateCart(CartVO cart);

    void deleteCart(int cartID);
}
