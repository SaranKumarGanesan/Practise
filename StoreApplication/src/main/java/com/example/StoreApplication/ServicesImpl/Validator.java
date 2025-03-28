package com.example.StoreApplication.ServicesImpl;

import com.example.StoreApplication.AppConstants.AppConstants;
import com.example.StoreApplication.Model.Cart;

public class Validator {
	public static void userIdNull(Long userId) {
		if (userId == null) {
			throw new RuntimeException(AppConstants.USER_NOT_FOUND_EXCEPTION);
		}
	}

	public static void cartNull(Cart cart, Long userId) {
		if (cart == null) {
			throw new RuntimeException(AppConstants.CART_NOT_FOUND + userId);
		}
	}
}

