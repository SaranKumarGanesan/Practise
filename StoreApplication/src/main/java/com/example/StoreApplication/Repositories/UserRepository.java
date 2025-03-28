package com.example.StoreApplication.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.StoreApplication.Model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmailAndPassword(String email, String password);
	Optional<User> findByEmail(String email);

}
