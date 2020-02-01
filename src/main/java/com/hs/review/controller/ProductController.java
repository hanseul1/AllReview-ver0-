package com.hs.review.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hs.review.dto.Product;
import com.hs.review.service.ProductService;
import com.hs.review.util.RestUtil;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping("/product")
	public ResponseEntity<Map<String,Object>> searchAll(){
		return RestUtil.handleSuccess(service.searchAll());
	}
	
	@GetMapping("/product/{category}")
	public ResponseEntity<Map<String,Object>> searchByCategory
							(@PathVariable int category){
		return RestUtil.handleSuccess(service.searchByCategory(category));
	}
	
	@PostMapping("/product")
	public ResponseEntity<Map<String,Object>> insert
							(@RequestBody Product product){
		service.insertProduct(product);
		return RestUtil.handleSuccess("success");
	}
	
	@PutMapping("/product")
	public ResponseEntity<Map<String, Object>> update
							(@RequestBody Product product){
		service.updateProduct(product);
		return RestUtil.handleSuccess("success");
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Map<String,Object>> delete(@PathVariable int id){
		service.deleteProduct(id);
		return RestUtil.handleSuccess("success");
	}
}
