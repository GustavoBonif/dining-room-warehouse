package com.diningroom.warehouse.dto;

import com.diningroom.warehouse.entities.Stock;

public class StockDTO {

    private Long id;

    private Long productId;

    private int quantityAvailable;

    public StockDTO() {

    }

    public StockDTO(Long id, Long productId, int quantityAvailable) {
        this.id = id;
        this.productId = productId;
        this.quantityAvailable = quantityAvailable;
    }

    public StockDTO(Stock stock) {
        this.id = stock.getId();
        this.productId = stock.getProductId();
        this.quantityAvailable = stock.getQuantityAvailable();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}