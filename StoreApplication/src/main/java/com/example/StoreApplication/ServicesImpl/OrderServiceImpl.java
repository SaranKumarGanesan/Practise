package com.example.StoreApplication.ServicesImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StoreApplication.AppConstants.AppConstants;
import com.example.StoreApplication.Dto.ProductDTO;
import com.example.StoreApplication.Dto.ResponseDTO;
import com.example.StoreApplication.Exceptions.RuntimeExceptions;
import com.example.StoreApplication.Model.Cart;
import com.example.StoreApplication.Model.CartItem;
import com.example.StoreApplication.Model.Order;
import com.example.StoreApplication.Model.OrderStatus;
import com.example.StoreApplication.Model.Product;
import com.example.StoreApplication.Repositories.CartRepository;
import com.example.StoreApplication.Repositories.OrderRepository;
import com.example.StoreApplication.Repositories.ProductRepositoy;
import com.example.StoreApplication.Services.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepositoy productRepositoy;
	
	
	@Transactional
	@Override
	public ResponseDTO checkout(Long userId) {
		// TODO Auto-generated method stub
		if(userId == null) {
			throw new RuntimeExceptions(AppConstants.USER_NOT_FOUND_EXCEPTION);
		}
		Cart cart = cartRepository.findByUserId(userId);
		if (cart == null) {
			throw new RuntimeException(AppConstants.CART_NOT_FOUND + userId);
		}
		
		
		BigDecimal totalAmount = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);

		for (CartItem cartItem : cart.getItems()) {
			Product product = cartItem.getProduct();
			BigDecimal currentPrice = product.getPrice();

			if (cartItem.getQuantity() > product.getAvailable()) {
				throw new RuntimeException(AppConstants.INSUFFICIENT_STOCK + product.getName());
			}

			BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
			BigDecimal itemTotal = quantity.multiply(currentPrice);
			totalAmount = totalAmount.add(itemTotal);
		}
		Order order = new Order();
		order.setUser(cart.getUser());
		order.setTotalAmount(totalAmount);
		order.setStatus(OrderStatus.COMPLETED);
		order.setOrderDate(new Date());

		orderRepository.save(order);

		for (CartItem cartItem : cart.getItems()) {
			Product product = cartItem.getProduct();
			product.setAvailable(product.getAvailable() - cartItem.getQuantity());
			productRepositoy.save(product);
		}

		cart.getItems().clear();
		cartRepository.save(cart);

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage(AppConstants.ORDER_PLACED_SUCCESFULLY + totalAmount);

		return responseDTO;

	}

}
