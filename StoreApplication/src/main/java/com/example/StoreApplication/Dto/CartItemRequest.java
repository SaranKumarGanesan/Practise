package com.example.StoreApplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CartItemRequest {

	private Long id;
	private Integer quantity;
	
	public CartItemRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItemRequest(Long id, Integer quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItemRequest [id=" + id + ", quantity=" + quantity + "]";
	}
	
	
	

}
