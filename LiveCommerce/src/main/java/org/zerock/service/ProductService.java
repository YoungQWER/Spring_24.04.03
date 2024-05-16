package org.zerock.service;

import java.util.List;
import org.zerock.domain.ProductVO;

public interface ProductService {

    // 제품 등록
    void registerProduct(ProductVO product);
    
    // 제품 조회
    ProductVO getProduct(int productID);
    
    // 모든 제품 조회
    List<ProductVO> getAllProducts();
    
    // 제품 수정
    void updateProduct(ProductVO product);
    
    // 제품 삭제
    void deleteProduct(int productID);
    
    // categoryId로 해당 카테고리에 속하는 상품을 가져오는 메소드
    List<ProductVO> getProductsByCategory(int categoryId);
}
