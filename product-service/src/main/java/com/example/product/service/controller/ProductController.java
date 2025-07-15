package com.example.product.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.product.service.entity.Product;
import com.example.product.service.repository.ProductRepository;

//import main.java.com.programming.techie.productservice.controller.ResponseStatus;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	//create product
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product addProduct(@RequestBody Product product) {
		
		try {
			System.out.println("test1"+Product.class.getName());
	
		}
		catch(Exception e) {
			System.out.println("test8"+e);
			e.printStackTrace();
			System.out.println("test2"+e);
		}
		System.out.println("test3");
		 productRepository.save(product);
		 System.out.println("test10"+product);
		 //Logger logger=Logger.getLogger(ProductController.class.getName());
		 //System.out.println("test11--"+logger);
		
		 return product;
	}
	
	//Get all products
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	//Get Product by Id
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId){
		System.out.println("test5");
		Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product not found with id:"+productId));
		System.out.println("test4");
		return ResponseEntity.ok(product);
	}

}

