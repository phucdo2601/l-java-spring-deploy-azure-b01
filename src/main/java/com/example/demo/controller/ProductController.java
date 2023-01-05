package com.example.demo.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MessageNoti;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ProductService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * @ApiOperation -- mot ta khai quat ve cong dung cua api dung de lam gi
	 * @return
	 */
	@ApiOperation("This Api using get list product")
	@GetMapping("/listProduct")
	public ResponseEntity<List<ProductEntity>> getListProduct(){
		List<ProductEntity> listPro = productService.getListProduct();
		return new ResponseEntity(listPro, HttpStatus.OK);
	}
	
	/**
	 * @ApiIgnore -- nham de an api dc chi dinh khi dat tu khoa nay
	 * @param id
	 * @return
	 */
//	@ApiIgnore
	@GetMapping("/detailById/{id}")
	public ResponseEntity<ProductEntity> getProductById(@PathVariable("id") int id) {
		if (!productService.existsById(id)) {
			return new ResponseEntity(new MessageNoti("No Existed!"), HttpStatus.NOT_FOUND);
		} 
		ProductEntity productEn = productService.getProductById(id).get();
		return new ResponseEntity(productEn, HttpStatus.OK);
	}
	
	
	@GetMapping("/detailByName/{name}")
	public ResponseEntity<ProductEntity> getProductByName(@PathVariable("name") String name) {
		if (!productService.existsByName(name)) {
			return new ResponseEntity(new MessageNoti("No Existed!"), HttpStatus.NOT_FOUND);
		} 
		ProductEntity productEn = productService.getProductByName(name).get();
		return new ResponseEntity(productEn, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createProduct")
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
		if (StringUtils.isBlank(productDto.getName())) {
			return new ResponseEntity(new MessageNoti("Name of product is not blank!"), HttpStatus.BAD_REQUEST);
		}
		if (productDto.getPrice() < 0) {
			return new ResponseEntity(new MessageNoti("Price of product is larger than 0!"), HttpStatus.BAD_REQUEST);
		}
		if (productDto.getQuantity() < 0) {
			return new ResponseEntity(new MessageNoti("Quantity of product is larger than 0!"), HttpStatus.BAD_REQUEST);
		}
		if (productService.existsByName(productDto.getName())) {
			return new ResponseEntity(new MessageNoti("Product with this name is existed!"), HttpStatus.BAD_REQUEST);
		}
		String name = productDto.getName();
		int quantity = productDto.getQuantity();
		double price = productDto.getPrice();
		ProductEntity producto = new ProductEntity(name, quantity, price);
        productService.saveProduct(producto);
        return new ResponseEntity(new MessageNoti("Product save sucessfully"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<?> updateProductById(@PathVariable("id") int id, @RequestBody ProductDto productDto) {
		if (!productService.existsById(id)) {
			return new ResponseEntity(new MessageNoti("No product existed with id!"), HttpStatus.NOT_FOUND);
		}
		if (productService.existsByName(productDto.getName()) && productService.getProductByName(productDto.getName()).get().getId() != id) {
			return new ResponseEntity(new MessageNoti("Product is existed with this name"), HttpStatus.NOT_FOUND);
		}
		if (StringUtils.isBlank(productDto.getName())) {
			return new ResponseEntity(new MessageNoti("Name of product is not blank!"), HttpStatus.BAD_REQUEST);
		}
		if (productDto.getPrice() < 0) {
			return new ResponseEntity(new MessageNoti("Price of product is larger than 0!"), HttpStatus.BAD_REQUEST);
		}
		if (productDto.getQuantity() < 0) {
			return new ResponseEntity(new MessageNoti("Quantity of product is larger than 0!"), HttpStatus.BAD_REQUEST);
		}
		if (productService.existsByName(productDto.getName())) {
			return new ResponseEntity(new MessageNoti("Product with this name is existed!"), HttpStatus.BAD_REQUEST);
		}
		String name = productDto.getName();
		int quantity = productDto.getQuantity();
		double price = productDto.getPrice();
		ProductEntity productEntity = productService.getProductById(id).get();
		productEntity.setName(name);
		productEntity.setPrice(price);
		productEntity.setQuantity(quantity);
		productService.saveProduct(productEntity);
		return new ResponseEntity(new MessageNoti("Update Product is successfully!"), HttpStatus.OK); 
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deletePro/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable("id") int id) {
		if (!productService.existsById(id)) {
			return new ResponseEntity(new MessageNoti("No Existed!"), HttpStatus.NOT_FOUND);
		}
		
		productService.delete(id);
		return new ResponseEntity(new MessageNoti("Product delete sucessfully!"), HttpStatus.OK);
	}
}
