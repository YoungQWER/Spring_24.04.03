package org.zerock.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CategoryVO;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CategoryMapperTest {
	
	@Autowired
	private CategoryMapper category;

	@Test //C
	public void testC() {
		CategoryVO ca = CategoryVO.builder()
				.categoryName("외투")
				.build();
		log.info(ca);
		category.createCategory(ca);
	}
	@Test //R
	public void testR() {
		
		CategoryVO ca = category.getCategory(1);
		
		log.info(ca);
	}
	@Test //U
	public void testU() {
		
		CategoryVO ca = CategoryVO.builder()
				.categoryName("자켓")
				.categoryID(1)
				.build();
		category.updateCategory(ca);
		log.info(ca);
	}
	@Test //D
	public void testD() {
		category.deleteCategory(4);
	}
	 @Test
	    public void testGetAllCategories() {
	        category.getAllCategories().forEach(category -> log.info(category));
	    }

}
