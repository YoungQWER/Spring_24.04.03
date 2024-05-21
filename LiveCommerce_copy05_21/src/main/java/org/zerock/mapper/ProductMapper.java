package org.zerock.mapper;

import java.util.List;
import org.zerock.domain.ProductVO;

public interface ProductMapper {

    // 제품 등록
    void insertProduct(ProductVO product);
    
    // 제품 조회
    ProductVO selectProduct(int productID);
    
    // 모든 제품 조회
    List<ProductVO> selectAllProducts();
    
    // 제품 수정
    void updateProduct(ProductVO product);
    
    // 제품 삭제
    void deleteProduct(int productID);
    
    // categoryId로 해당 카테고리에 속하는 상품을 가져오는 메소드
    List<ProductVO> getProductsByCategory(int categoryId);
}