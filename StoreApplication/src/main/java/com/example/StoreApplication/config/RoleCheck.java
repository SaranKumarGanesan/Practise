package com.example.StoreApplication.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.StoreApplication.AppConstants.AppConstants;
import com.example.StoreApplication.Exceptions.RuntimeExceptions;
import com.example.StoreApplication.ServicesImpl.AuthorizationService;
@Aspect
@Component
public class RoleCheck {

	@Autowired
	private AuthorizationService authorizationService;

	@Before("@annotation(roleRequired)")
	public void checkRole(RoleRequired roleRequired) {
		if (!authorizationService.hasRequiredRole(roleRequired.value())) {
			throw new RuntimeExceptions(AppConstants.UNAUTHORIZED_ACCESS_MESSAGE);
		}
	}
}
