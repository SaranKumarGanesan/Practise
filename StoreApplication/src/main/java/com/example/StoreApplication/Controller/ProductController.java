package com.example.StoreApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoreApplication.Dto.ProductDTO;
import com.example.StoreApplication.Dto.ResponseDTO;
import com.example.StoreApplication.Dto.Role;
import com.example.StoreApplication.Model.Product;
import com.example.StoreApplication.Services.ProductService;
import com.example.StoreApplication.config.RoleRequired;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RoleRequired(value = Role.ADMIN)
	@PostMapping("/save")
	public ResponseEntity<ProductDTO> saveProduct(@RequestBody Product product){
		ProductDTO saved = productService.saveProduct(product);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
		
	}
	@GetMapping("/")
	public ResponseEntity<List<ProductDTO>> getProducts() {
		List<ProductDTO> products = productService.getAll();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
//	@PutMapping("/modify")
//	public ResponseEntity<ResponseDTO> updateProduct(@RequestBody Product product){
//		ResponseDTO updateProduct = productService.updateProduct(product);
//		return new ResponseEntity<>(updateProduct,HttpStatus.OK);
//	}
	
	@PutMapping("/modify/{id}")
	public ResponseEntity<ResponseDTO> updateProduct(@RequestBody Product product,@PathVariable Long id){
		ResponseDTO updateProduct = productService.updateProduct(id,product);
		return new ResponseEntity<>(updateProduct,HttpStatus.OK);
	}

}
