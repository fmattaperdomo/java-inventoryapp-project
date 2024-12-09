package com.fmattaperdomo.inventoryapp.repositories;

import com.fmattaperdomo.inventoryapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
