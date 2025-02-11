package com.example.StoreApplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.StoreApplication.Model.Product;

@Repository
public interface ProductRepositoy extends JpaRepository<Product, Long>{

}
