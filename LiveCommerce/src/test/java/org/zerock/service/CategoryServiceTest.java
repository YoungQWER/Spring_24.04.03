package org.zerock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

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
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testCreateCategory() {
        CategoryVO category = new CategoryVO();
        category.setCategoryName("Test Category");
        categoryService.createCategory(category);
        assertNotNull(category.getCategoryID()); // 생성된 카테고리 ID가 null이 아닌지 확인
    }

    @Test
    public void testGetCategory() {

        // 생성된 카테고리의 ID를 가져와서 조회
        int categoryId = 1; // 예시로 CategoryID를 1로 지정
        CategoryVO createdCategory = categoryService.getCategory(categoryId);
        log.info(createdCategory);
    }




    @Test
    public void testUpdateCategory() {
        // 생성된 카테고리의 ID를 가져와서 조회
        int categoryId = 1; // 예시로 CategoryID를 1로 지정
        CategoryVO createdCategory = categoryService.getCategory(categoryId);

        // 카테고리 이름을 업데이트
        createdCategory.setCategoryName("Updated Test Category");
        categoryService.updateCategory(createdCategory);
        
        // 업데이트된 카테고리를 다시 조회
        CategoryVO updatedCategory = categoryService.getCategory(categoryId);
        log.info(updatedCategory);
    }



    @Test
    public void testDeleteCategory() {
        
     categoryService.deleteCategory(7);
 }

    @Test
    public void testGetAllCategories() {
        List<CategoryVO> categories = categoryService.getAllCategories();
       log.info(categories);
    }
}