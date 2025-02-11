package com.example.StoreApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoreApplication.Dto.ResponseDTO;
import com.example.StoreApplication.Model.User;
import com.example.StoreApplication.Services.OrderService;
import com.example.StoreApplication.ServicesImpl.UserSessionServiceImpl;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private  OrderService orderService;
	@Autowired
	private UserSessionServiceImpl userSessionServiceImpl;

	
	@PostMapping("/orders")
	public ResponseEntity<ResponseDTO> checkout(HttpSession session) {
		User user = userSessionServiceImpl.getUserIdFromSession(session);
		ResponseDTO checkout = orderService.checkout(user.getId());
		return new ResponseEntity<>(checkout, HttpStatus.OK);
	}


}
