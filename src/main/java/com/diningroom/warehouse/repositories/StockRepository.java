package com.diningroom.warehouse.repositories;

import com.diningroom.warehouse.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByProductId(Long productId);
}
