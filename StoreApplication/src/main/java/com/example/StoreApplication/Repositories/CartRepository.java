package com.example.StoreApplication.Repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.StoreApplication.Model.Cart;
import com.example.StoreApplication.Model.User;
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

	Cart findByUserId(Long userId);
	Optional<Cart> findByUser(User user);

}
