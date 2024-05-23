package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.ProductVO;

import org.zerock.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductMapper productMapper;

	@Override
	public void registerProduct(ProductVO product) {
		productMapper.insertProduct(product);
		
	}

	@Override
	public ProductVO getProduct(int productID) {
	return productMapper.selectProduct(productID);
		
	}

	@Override
	public List<ProductVO> getAllProducts() {
		return productMapper.selectAllProducts();
	}

	@Override
	public void updateProduct(ProductVO product) {
		productMapper.updateProduct(product);
		
	}

	@Override
	public void deleteProduct(int productID) {
		productMapper.deleteProduct(productID);
		
	}
    @Override
    public List<ProductVO> getProductsByCategory(int categoryId) {
        return productMapper.getProductsByCategory(categoryId);
    }
    
    public List<ProductVO> searchProductsByName(String productName) {
        return productMapper.searchProductsByName(productName);
    }
    @Override
    public Integer getCategoryIdByProductId(int productId) {
        return productMapper.findCategoryIdByProductId(productId);
    }

}
