package com.example.StoreApplication.Model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Cart> carts = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();

	public User() {
		super();
	}

	public User(Long id, String name, String email, String password, List<Cart> carts, List<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.carts = carts;
		this.orders = orders;
	}

	public User(String name, String email, String password, List<Cart> carts, List<Order> orders) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.carts = carts;
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", carts=" + carts
				+ ", orders=" + orders + "]";
	}
	
	
	
	

}
