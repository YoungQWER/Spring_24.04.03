package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;
import org.zerock.domain.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

//    @Before
//    public void setUp() {
//        // 테스트를 위한 데이터 삽입
//        ProductVO product = ProductVO.builder()
//                .ProductName("Test Product")
//                .Description("Test Description")
//                .Price(10000)
//                .Photo("test_photo.jpg") // 이 부분은 데이터베이스에 맞게 수정해야 합니다.
//                .build();
//        productMapper.insertProduct(product);
//    }


    @Test
    public void testInsertProduct() {
        ProductVO product = ProductVO.builder()
                .productName("New Product")
                .description("New Description")
                .price(20000)
                .photo("new_photo.jpg") // 이 부분은 데이터베이스에 맞게 수정해야 합니다.
                .build();

        productMapper.insertProduct(product);

        assertNotNull(product.getProductId()); // 제품 ID가 생성되었는지 확인
    }

    @Test
    public void testSelectProduct() {
        ProductVO product = productMapper.selectProduct(1); // 제품 ID에 따라 조회

       log.info(product);
    }

    @Test
    public void testSelectAllProducts() {
        List<ProductVO> productList = productMapper.selectAllProducts();
           log.info(productList);
    }

    @Test
    public void testUpdateProduct() {
        ProductVO product = productMapper.selectProduct(1); // 제품 ID에 따라 수정
        product.setProductName("Updated Product");

        productMapper.updateProduct(product);

        ProductVO updatedProduct = productMapper.selectProduct(2); // 제품 ID에 따라 수정
       log.info(updatedProduct);
    }

    @Test
    public void testDeleteProduct() {
        productMapper.deleteProduct(1); // 제품 ID에 따라 수정

        
        
    }
}