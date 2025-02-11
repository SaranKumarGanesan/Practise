package com.example.StoreApplication.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StoreApplication.AppConstants.AppConstants;
import com.example.StoreApplication.Dto.ProductDTO;
import com.example.StoreApplication.Dto.ResponseDTO;
import com.example.StoreApplication.Model.Product;
import com.example.StoreApplication.Repositories.ProductRepositoy;
import com.example.StoreApplication.Services.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepositoy productRepositoy;

	ResponseDTO responseDTO = new ResponseDTO();
	
	@Override
	public ProductDTO saveProduct(Product product) {
		// TODO Auto-generated method stub
		Product save = productRepositoy.save(product);
		ProductDTO productDTO = new ProductDTO();
		BeanUtils.copyProperties(save, productDTO);
		return productDTO;
	}

	@Override
	public List<ProductDTO> getAll() {
		// TODO Auto-generated method stub
		List<Product> products = (List<Product>) productRepositoy.findAll();
		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
		for(Product product: products) {
			ProductDTO productDTO = new ProductDTO();
			BeanUtils.copyProperties(product, productDTO);
			dtos.add(productDTO);
		}
		return dtos;
	}

//	@Override
//	public ResponseDTO updateProduct(Long id,Product product) {
//		// TODO Auto-generated method stub
//		System.out.println("___________2 id:"+id);
//		Optional<Product> productOpt = productRepositoy.findById(id);
//		System.out.println("___________3 ID: "+id);
//		if(productOpt.isPresent()) {
//			Product product2 = productOpt.get();
//			product2.setName(product.getName());
//			product2.setDescription(product.getDescription());
//			product2.setPrice(product.getPrice());
//			product2.setAvailable(product.getAvailable());
//			productRepositoy.save(product2);
//			
//			responseDTO.setMessage(AppConstants.PRODUCT_UPDATED_SUCCESFULLY);
//			return responseDTO;
//		}
//		responseDTO.setMessage(AppConstants.PRODUCT_NOT_UPDATED_SUCCESFULLY);
//		return responseDTO;
//	}

	@Override
	public ResponseDTO updateProduct(Long id, Product product) {
	
		if (!productRepositoy.existsById(id)) {
		

			responseDTO.setMessage(AppConstants.PRODUCT_UPDATED_SUCCESFULLY);
			return responseDTO;
			//return new ResponseDto(AppConstants.PRODUCT_NOT_UPDATED_SUCCESFULLY);
		}
		product.setId(id);
		productRepositoy.save(product);

		responseDTO.setMessage(AppConstants.PRODUCT_NOT_UPDATED_SUCCESFULLY);
		return responseDTO;
		//return new ResponseDto(AppConstants.PRODUCT_UPDATED_SUCCESFULLY);
	}
}
