package com.example.StoreApplication.Services;

import com.example.StoreApplication.Dto.ResponseDTO;
import com.example.StoreApplication.Dto.UserDTO;
import com.example.StoreApplication.Model.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {

	public UserDTO registerUser(User user);
	
	public ResponseDTO loginUser(User user, HttpSession session);
	
	public User getUser(Long userId);
	

}
