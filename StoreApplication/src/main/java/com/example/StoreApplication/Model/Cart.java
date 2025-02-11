package com.example.StoreApplication.Model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", fetch = FetchType.LAZY)
	private List<CartItem> items = new ArrayList<>();

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Long id, User user, List<CartItem> items) {
		super();
		this.id = id;
		this.user = user;
		this.items = items;
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

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", items=" + items + "]";
	}
	
	
	
	

}
