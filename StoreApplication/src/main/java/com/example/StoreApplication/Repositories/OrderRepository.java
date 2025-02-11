package com.example.StoreApplication.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.StoreApplication.Model.Order;
@Repository
public interface OrderRepository extends CrudRepository<Order , Long> {

}
