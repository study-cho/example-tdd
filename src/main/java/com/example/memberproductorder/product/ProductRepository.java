package com.example.memberproductorder.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
