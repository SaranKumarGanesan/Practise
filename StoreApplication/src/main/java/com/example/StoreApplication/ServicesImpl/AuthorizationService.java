package com.example.StoreApplication.ServicesImpl;

import org.springframework.stereotype.Service;

import com.example.StoreApplication.Dto.Role;

@Service
public class AuthorizationService {
	public Role getCurrentUserRole() {
		return Role.ADMIN;
	}

	public boolean hasRequiredRole(Role[] requiredRoles) {
		Role currentRole = getCurrentUserRole();
		for (Role role : requiredRoles) {
			if (role == currentRole) {
				return true;
			}
		}
		return false;
	}
}