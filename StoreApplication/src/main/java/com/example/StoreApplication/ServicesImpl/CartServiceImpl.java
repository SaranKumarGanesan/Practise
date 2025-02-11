package com.example.StoreApplication.ServicesImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StoreApplication.AppConstants.AppConstants;
import com.example.StoreApplication.Dto.CartDetailsDTO;
import com.example.StoreApplication.Dto.CartItemDetailsDTO;
import com.example.StoreApplication.Dto.ResponseDTO;
import com.example.StoreApplication.Exceptions.RuntimeExceptions;
import com.example.StoreApplication.Model.Cart;
import com.example.StoreApplication.Model.CartItem;
import com.example.StoreApplication.Model.Product;
import com.example.StoreApplication.Model.User;
import com.example.StoreApplication.Repositories.CartItemRepository;
import com.example.StoreApplication.Repositories.CartRepository;
import com.example.StoreApplication.Repositories.ProductRepositoy;
import com.example.StoreApplication.Repositories.UserRepository;
import com.example.StoreApplication.Services.CartService;

import jakarta.transaction.Transactional;
@Service 
public class CartServiceImpl implements CartService {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepositoy productRepositoy;
	
	@Autowired
	private UserRepository userRepository;
	
	ResponseDTO responseDTO = new ResponseDTO();
	
	@Transactional
	@Override
	public void addItemToCart(Long userId, Long productId, Integer quantity) {
		// TODO Auto-generated method stub
		Validator.userIdNull(userId);
		Cart cart = cartRepository.findByUserId(userId);
		User user = userRepository.findById(userId).get();
		
		if (cart == null) {
			cart = new Cart();
			cart.setUser(user);
			cartRepository.save(cart);
		}
		CartItem existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

		Product product = productRepositoy.findById(productId).get();

		if (existingItem != null) {
			existingItem.setQuantity(existingItem.getQuantity() + quantity);
			cartItemRepository.save(existingItem);
		} else {
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItemRepository.save(cartItem);
		}
	}

	@Override
	public CartDetailsDTO displayCartContent(Long userId) {
		// TODO Auto-generated method stub
		Validator.userIdNull(userId);
		Cart cart = cartRepository.findByUserId(userId);
		Validator.cartNull(cart, userId);		List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
		List<CartItemDetailsDTO> itemDetails = new ArrayList<>();
		BigDecimal total = BigDecimal.ZERO;
		int ordinal = 1;

		for (CartItem cartItem : cartItems) {
			Product product = cartItem.getProduct();

			BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
			BigDecimal price = product.getPrice();
			BigDecimal subtotal = quantity.multiply(price);
			total = total.add(subtotal);
			CartItemDetailsDTO details = new CartItemDetailsDTO(ordinal++, product.getName(), cartItem.getQuantity(),
					subtotal);
			itemDetails.add(details);
		}
		return new CartDetailsDTO(itemDetails, total);
	}

	@Transactional
	@Override
	public ResponseDTO removeItemFromCart(Long userId, Long cartItemId) {
		// TODO Auto-generated method stub
		Validator.userIdNull(userId);

		Cart cart = cartRepository.findByUserId(userId);

		Validator.cartNull(cart, userId);
		CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

		if (cartItem == null || !cartItem.getCart().equals(cart)) {
			throw new RuntimeExceptions(
					//AppConstants.CART_ITEM_WITH_ID + cartItemId + AppConstants.NOT_FOUND + userId
					String.format(AppConstants.CART_ITEM_NOT_FOUND,cartItem,userId));
			
		}
		cartItemRepository.deleteById(cartItemId);

		responseDTO.setMessage(
				//AppConstants.CART_ITEM + cartItemId + AppConstants.REMOVED_SUCCESFULLY
				String.format(AppConstants.CART_ITEM_REMOVED_SUCCESFULLY,cartItem));
		return responseDTO;
	}

	@Transactional
	@Override
	public ResponseDTO updateItemQuantity(Long userId, Long cartItemId, int quantity) {
		// TODO Auto-generated method stub
		Validator.userIdNull(userId);
		Cart cart = cartRepository.findByUserId(userId);

		 Validator.cartNull(cart, userId);

		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);

		if (cartItem.isEmpty()) {
			throw new RuntimeExceptions(
					//AppConstants.CART_ITEM + cartItemId + AppConstants.NOT_FOUND + userId
					String.format(AppConstants.CART_ITEM_NOT_FOUND,cartItem,userId));
		}
		CartItem cartItem2 = cartItem.get();
		if (quantity <= 0) {
			throw new RuntimeExceptions(AppConstants.QUANTITY_GREATER_THAN_ZERO);
		}

		cartItem2.setQuantity(quantity);
		cartItemRepository.save(cartItem2);

		responseDTO.setMessage(
				//AppConstants.CART_ITEM + cartItemId + AppConstants.QUANTITY_UPDATED + quantity
				String.format(AppConstants.CART_ITEM_QUANTITY_UPDATED,cartItemId,quantity));
		return responseDTO;

	}
			
}
