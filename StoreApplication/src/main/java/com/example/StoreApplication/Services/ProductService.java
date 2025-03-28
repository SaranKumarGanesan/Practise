package com.example.StoreApplication.Services;

import java.util.List;

import com.example.StoreApplication.Dto.ProductDTO;
import com.example.StoreApplication.Dto.ResponseDTO;
import com.example.StoreApplication.Model.Product;


public interface ProductService {

	ProductDTO saveProduct(Product product);
	List<ProductDTO> getAll();
	//ResponseDTO updateProduct(Product product , Long id);
	ResponseDTO updateProduct(Long id, Product product);

}
