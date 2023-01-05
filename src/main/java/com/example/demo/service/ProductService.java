package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.ProductEntity;

public interface ProductService {

	boolean existsByName(String name);

	boolean existsById(int id);

	ProductEntity updateProduct(ProductEntity p);

	void delete(int id);

	void saveProduct(ProductEntity productEn);

	Optional<ProductEntity> getProductById(int id);

	List<ProductEntity> getListProduct();

	Optional<ProductEntity> getProductByName(String name);

}
