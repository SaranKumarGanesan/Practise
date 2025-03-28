package com.example.StoreApplication.Services;

import com.example.StoreApplication.Dto.CartDetailsDTO;
import com.example.StoreApplication.Dto.ResponseDTO;

public interface CartService {

	public void addItemToCart(Long userId, Long productId, Integer quantity);

	public CartDetailsDTO displayCartContent(Long userId);

	public ResponseDTO removeItemFromCart(Long userId, Long cartItemId);

	public ResponseDTO updateItemQuantity(Long userId, Long cartItemId, int quantity);

}
