package com.example.StoreApplication.ServicesImpl;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.StoreApplication.AppConstants.AppConstants;
import com.example.StoreApplication.Exceptions.RuntimeExceptions;
import com.example.StoreApplication.Model.User;
import com.example.StoreApplication.Services.UserService;

import jakarta.servlet.http.HttpSession;
@Service
public class UserSessionServiceImpl {
	
	@Autowired
	private UserService userService;
	

	public User getUserIdFromSession(HttpSession session) {
		Long userId = (Long) session.getAttribute(AppConstants.USER_ID);

		if (userId == null) {
			throw new RuntimeExceptions(AppConstants.UNAUTHORIZED_USER);
		}

		Long userid = (Long) session.getAttribute(AppConstants.USER_ID);

		return userService.getUser(userid);
	}

}
