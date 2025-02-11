package com.example.StoreApplication.ServicesImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.StoreApplication.AppConstants.AppConstants;
import com.example.StoreApplication.Dto.ResponseDTO;
import com.example.StoreApplication.Dto.UserDTO;
import com.example.StoreApplication.Exceptions.RuntimeExceptions;
import com.example.StoreApplication.Model.User;
import com.example.StoreApplication.Repositories.UserRepository;
import com.example.StoreApplication.Services.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
 
	@Override
	public UserDTO registerUser(User user) {
		// TODO Auto-generated method stub
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		
		if (existingUser.isPresent()) {
			throw new RuntimeExceptions(AppConstants.EMAIL_ALREADY_EXIST + user.getEmail());
		}
		String encode = encoder.encode(user.getPassword());
		user.setPassword(encode);
		User save = userRepository.save(user);
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(save, userDTO);
		return userDTO;

	}

	@Override
	public ResponseDTO loginUser(User user, HttpSession session) {
		// TODO Auto-generated method stub
		Optional<User> validUser = userRepository.findByEmail(user.getEmail());

				if (validUser.isPresent()) {
					User user2 = validUser.get();
					if (encoder.matches(user.getPassword(), user2.getPassword())) {
						session.setAttribute("userId", user2.getId());
						session.setAttribute("Login", true);
						ResponseDTO responseDTO = new ResponseDTO();
						responseDTO.setMessage(AppConstants.USER_LOGIN_SUCCESFULLY);
						responseDTO.setSession(session.getId());
						return responseDTO;
					} else {
						throw new RuntimeExceptions(AppConstants.INVALID_CREDENTIALS);
					}
				} else {
					throw new RuntimeExceptions(AppConstants.USER_NOT_FOUND + user.getEmail());
				}
			}

	@Override
	public User getUser(Long userId) {
		return userRepository.findById(userId).get();
	}
	}
