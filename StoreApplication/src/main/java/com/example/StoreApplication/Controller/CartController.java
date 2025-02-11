package com.example.StoreApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoreApplication.AppConstants.AppConstants;
import com.example.StoreApplication.Dto.AddToCartRequest;
import com.example.StoreApplication.Dto.CartDetailsDTO;
import com.example.StoreApplication.Dto.ResponseDTO;
import com.example.StoreApplication.Model.User;
import com.example.StoreApplication.Services.CartService;
import com.example.StoreApplication.ServicesImpl.CartServiceImpl;
import com.example.StoreApplication.ServicesImpl.UserSessionServiceImpl;


import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/carts")
public class CartController {
	
//	@Autowired
//	private CartServiceImpl cartService;
//	@Autowired
//	private UserSessionServiceImpl userSessionServiceImpl;

	private final CartService cartService;
	private final UserSessionServiceImpl userSessionServiceImpl;

	public CartController(CartService cartService, UserSessionServiceImpl userSessionServiceImpl) {
		super();
		this.cartService = cartService;
		this.userSessionServiceImpl = userSessionServiceImpl;
	}
	
	@PostMapping("/items/{itemId}")
	public ResponseEntity<String> addItemToCart(HttpSession session, @PathVariable("itemId") Long productId,
		  @RequestBody AddToCartRequest request) {
		
		
		User user = userSessionServiceImpl.getUserIdFromSession(session);
		
		cartService.addItemToCart(user.getId(), productId, request.getCount());
		
		return new ResponseEntity<>(AppConstants.ITEM_ADDED_TO_CART, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<CartDetailsDTO> viewCart(HttpSession session) {
		
		
		User user = userSessionServiceImpl.getUserIdFromSession(session);
		
		CartDetailsDTO displayCartContent = cartService.displayCartContent(user.getId());
		return new ResponseEntity<>(displayCartContent, HttpStatus.OK);
	}
	
	@DeleteMapping("/items/{itemId}")
	public ResponseEntity<ResponseDTO> removeItemFromCart(HttpSession session, @PathVariable("itemId") Long cartItemId) {
		User user = userSessionServiceImpl.getUserIdFromSession(session);
		ResponseDTO removeItemFromCart = cartService.removeItemFromCart(user.getId(), cartItemId);
		return new ResponseEntity<>(removeItemFromCart, HttpStatus.OK);
	}
	@PutMapping("/items/{itemId}")
	public ResponseEntity<ResponseDTO> updateItemQuantity(HttpSession session, @PathVariable("itemId") Long cartItemId,
		    @RequestBody AddToCartRequest request) {
		User user = userSessionServiceImpl.getUserIdFromSession(session);
		ResponseDTO updateItemQuantity = cartService.updateItemQuantity(user.getId(), cartItemId, request.getCount());
		return new ResponseEntity<>(updateItemQuantity, HttpStatus.OK);
	}
	
}
