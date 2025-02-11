package com.example.StoreApplication.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.StoreApplication.Model.CartItem;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	CartItem findByCartIdAndProductId(Long id, Long productId);
	List<CartItem> findByCartId(Long id);

}
