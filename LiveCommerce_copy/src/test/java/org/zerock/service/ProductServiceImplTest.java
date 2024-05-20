package org.zerock.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ProductVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ProductServiceImplTest {

	@Autowired
	private ProductService productService;
	
	@Test
	public void testRegisterProduct() {
		ProductVO product = ProductVO.builder()
				.productName("Test Product")
				.price(50000)
				.description("Test Description")
				.photo("apple_photo.jpg")
				.build();
		
		productService.registerProduct(product);
		
		log.info("Registered Product ID: " + product.getProductId());
	}
	
	@Test
	public void testGetProduct() {
		int productID = 1; // Change to the existing product ID in your database
		
		ProductVO product = productService.getProduct(productID);
		
		assertNotNull(product);
		log.info("Retrieved Product: " + product);
	}
	
	@Test
	public void testGetAllProducts() {
		List<ProductVO> productList = productService.getAllProducts();
		
		assertNotNull(productList);
		log.info("Retrieved Product List: " + productList);
	}
	
	@Test
	public void testUpdateProduct() {
		ProductVO product = productService.getProduct(11); // Change to the existing product ID in your database
		
		product.setPrice(15000); // Change the price
		
		productService.updateProduct(product);
		
		ProductVO updatedProduct = productService.getProduct(1); // Change to the same product ID used above
		
		
		log.info("Updated Product: " + updatedProduct);
	}
	
	@Test
	public void testDeleteProduct() {
		int productID = 10; // Change to the existing product ID in your database
		
		productService.deleteProduct(productID);
		
		assertNull(productService.getProduct(productID));
		log.info("Product with ID " + productID + " has been deleted.");
	}
}