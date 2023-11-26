package com.diningroom.warehouse.controllers;


import com.diningroom.warehouse.dto.StockDTO;
import com.diningroom.warehouse.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {
    private StockService service;

    @Autowired
    public StockController(StockService stockService) {
        this.service = stockService;
    }

    @GetMapping("/listAll")
    public List<StockDTO> listAllStocks() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> createStock(@RequestBody StockDTO stockDTO) {
        return service.mountNewStock(stockDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateStock(@PathVariable Long id, @RequestBody StockDTO stockDTO) {
        return service.updateStock(id, stockDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        return service.deleteStock(id);
    }

    @PostMapping("/createFromProduct")
    public ResponseEntity<String> createFromProduct(@RequestParam("productId") Long productId) {
        return service.mountNewStockFromNewProduct(productId);
    }

    @RequestMapping(value = "/updateStockByProduct", method = RequestMethod.PUT)
    public ResponseEntity<String> updateStockByProduct(@RequestParam("productId") Long productId, @RequestParam("quantityOrdered") int quantityOrdered) {
        return service.updateStockByProduct(productId, quantityOrdered);
    }
}
