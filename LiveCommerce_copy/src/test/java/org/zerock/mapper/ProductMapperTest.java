package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ProductVO;

import lombok.extern.log4j.Log4j;

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
    
    


    @Test
    public void testgetListWithPaging() {
        // 테스트용 데이터베이스에 새로운 데이터 추가

        // getListWithPaging 메서드 호출
        Criteria cri = new Criteria(1, 10);
        List<ProductVO> result = productMapper.getListWithPaging(cri);

        // 결과 검증
        assertEquals(10, result.size()); // 예상되는 결과의 크기와 같은지 확인
        
        // 추가적인 검증: 첫 번째 제품과 마지막 제품이 예상한 대로인지 확인
        ProductVO firstProduct = result.get(0);
        ProductVO lastProduct = result.get(result.size() - 1);
        
        // 예상되는 첫 번째 제품과 실제로 첫 번째 제품이 같은지 확인
        assertEquals(1, firstProduct.getProductId()); // 첫 번째 페이지의 첫 번째 제품의 ID는 1이어야 함
        // 예상되는 마지막 제품과 실제로 마지막 제품이 같은지 확인
        // 이 부분은 데이터베이스의 상태에 따라 유동적일 수 있으므로 적절한 값을 설정해야 함
        // 마지막 페이지의 마지막 제품의 ID는 어떤 값이어야 함

        // 추가적인 결과 검증 코드 추가 가능
    }

        
}