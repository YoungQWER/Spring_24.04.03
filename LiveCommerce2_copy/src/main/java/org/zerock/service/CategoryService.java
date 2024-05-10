package org.zerock.service;

import java.util.List;
import org.zerock.domain.CategoryVO;

public interface CategoryService {

    void createCategory(CategoryVO category);

    CategoryVO getCategory(int categoryID);

    void updateCategory(CategoryVO category);

    void deleteCategory(int categoryID);

    List<CategoryVO> getAllCategories();
}