package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductEntity> getListProduct() {
		return productRepository.findAll();
	}

	@Override
	public Optional<ProductEntity> getProductById(int id) {
		return productRepository.findById(id);
	}
	
	@Override
	public Optional<ProductEntity> getProductByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public void saveProduct(ProductEntity productEn) {
		productRepository.save(productEn);
	}

	@Override
	public void delete(int id) {
		productRepository.deleteById(id);
	}

	@Override
	public ProductEntity updateProduct(ProductEntity p) {
		ProductEntity existingProduct = productRepository.findById(p.getId()).orElse(null);
		existingProduct.setName(p.getName());
		existingProduct.setPrice(p.getPrice());
		existingProduct.setQuantity(p.getQuantity());
		return productRepository.save(existingProduct);

	}

	@Override
	public boolean existsById(int id) {
		return productRepository.existsById(id);
	}

	@Override
	public boolean existsByName(String name) {
		return productRepository.existsByName(name);
	}
}
