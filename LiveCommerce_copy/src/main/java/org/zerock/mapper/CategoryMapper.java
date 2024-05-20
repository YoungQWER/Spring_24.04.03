package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import org.zerock.domain.CategoryVO;


public interface CategoryMapper {

    void createCategory(CategoryVO category);

    CategoryVO getCategory(@Param("categoryID") int categoryID);

    void updateCategory(CategoryVO category);

    void deleteCategory(@Param("categoryID") int categoryID);

    List<CategoryVO> getAllCategories();
    
}