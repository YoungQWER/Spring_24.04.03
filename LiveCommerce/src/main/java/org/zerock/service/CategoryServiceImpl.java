package org.zerock.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.CategoryVO;
import org.zerock.mapper.CategoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Override
    public void createCategory(CategoryVO category) {
        categoryMapper.createCategory(category);
    }

    @Override
    public CategoryVO getCategory(int categoryID) {
        return categoryMapper.getCategory(categoryID);
    }

    @Override
    public void updateCategory(CategoryVO category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteCategory(int categoryID) {
        categoryMapper.deleteCategory(categoryID);
    }

    @Override
    public List<CategoryVO> getAllCategories() {
        return categoryMapper.getAllCategories();
    }
}