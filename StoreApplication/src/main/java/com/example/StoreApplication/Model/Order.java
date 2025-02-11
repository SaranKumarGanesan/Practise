package com.example.StoreApplication.Model;

import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.PENDING;

	@Column(name = "order_date")
	private Date orderDate;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long id, User user, BigDecimal totalAmount, OrderStatus status, Date orderDate) {
		super();
		this.id = id;
		this.user = user;
		this.totalAmount = totalAmount;
		this.status = status;
		this.orderDate = orderDate;
	}

	public Order(User user, BigDecimal totalAmount, OrderStatus status, Date orderDate) {
		super();
		this.user = user;
		this.totalAmount = totalAmount;
		this.status = status;
		this.orderDate = orderDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", totalAmount=" + totalAmount + ", status=" + status
				+ ", orderDate=" + orderDate + "]";
	}
	
	

}
