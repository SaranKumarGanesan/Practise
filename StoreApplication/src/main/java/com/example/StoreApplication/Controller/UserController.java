package com.example.StoreApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoreApplication.Dto.ResponseDTO;
import com.example.StoreApplication.Dto.UserDTO;
import com.example.StoreApplication.Model.User;
import com.example.StoreApplication.Services.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
		UserDTO registerUser = userService.registerUser(user);
		return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> loginUser(@RequestBody User user, HttpSession session) {

		ResponseDTO loginUser = userService.loginUser(user, session);
		return new ResponseEntity<>(loginUser, HttpStatus.OK);
	}

	
	

}
