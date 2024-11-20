package com.ecommerce.productManagement.repository;

import com.ecommerce.productManagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}