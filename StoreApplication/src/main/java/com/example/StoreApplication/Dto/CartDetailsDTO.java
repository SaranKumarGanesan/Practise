package com.example.StoreApplication.Dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDetailsDTO {

	private List<CartItemDetailsDTO> items;
	private BigDecimal total;
	
	public CartDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartDetailsDTO(List<CartItemDetailsDTO> items, BigDecimal total) {
		super();
		this.items = items;
		this.total = total;
	}

	public List<CartItemDetailsDTO> getItems() {
		return items;
	}

	public void setItems(List<CartItemDetailsDTO> items) {
		this.items = items;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CartDetailsDTO [items=" + items + ", total=" + total + "]";
	}
	

}
