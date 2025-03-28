package com.example.StoreApplication.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CartItemDetailsDTO {

	private Integer ordinal;
	private String productName;
	private Integer quantity;
	private BigDecimal subtotal;
	
	public CartItemDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItemDetailsDTO(Integer ordinal, String productName, Integer quantity, BigDecimal subtotal) {
		super();
		this.ordinal = ordinal;
		this.productName = productName;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "CartItemDetailsDTO [ordinal=" + ordinal + ", productName=" + productName + ", quantity=" + quantity
				+ ", subtotal=" + subtotal + "]";
	}
	
	

	
}