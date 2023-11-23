package com.diningroom.warehouse.services;


import com.diningroom.warehouse.dto.StockDTO;
import com.diningroom.warehouse.entities.Stock;
import com.diningroom.warehouse.repositories.StockRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public ResponseEntity<StockDTO> findById(Long id) {
        Stock entity = stockRepository.findById(id).get();
        StockDTO dto = new StockDTO(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Transactional
    public List<StockDTO> listAll() {
        List<Stock> stocks = this.stockRepository.findAll();

        return stocks.stream()
                .map(this::stockToStockDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> mountNewStock(StockDTO stockDTO) {
        Optional<Stock> existingStock = stockRepository.findByProductId(stockDTO.getProductId());

        if(existingStock == null) {
            return new ResponseEntity<>("Produto com ID " + stockDTO.getProductId() + " não encontrado", HttpStatus.NOT_FOUND);
        }

        Stock newStock = new Stock();
        newStock.setProductId(stockDTO.getProductId());
        newStock.setQuantityAvailable(stockDTO.getQuantityAvailable());

        this.create(newStock);

        return new ResponseEntity<>("Estoque criado com sucesso.", HttpStatus.CREATED);
    }

    public void mountNewStockFromNewProduct(StockDTO stockDTO) {
        Optional<Stock> existingStock = stockRepository.findByProductId(stockDTO.getProductId());

        if(!existingStock.isPresent()) {
            throw new IllegalArgumentException("Produto com o id " + stockDTO.getProductId() + " não existe.");
        }

        Stock newStock = new Stock();
        newStock.setProductId(stockDTO.getProductId());
        newStock.setQuantityAvailable(10);

        this.create(newStock);
    }

    @Transactional
    public void create(Stock newStock) {
        this.stockRepository.save(newStock);
    }

    @Transactional
    public ResponseEntity<String> updateStock(Long id, StockDTO stockDTO) {
        if (!stockRepository.existsById(id)) {
            return new ResponseEntity<>("Estoque com o id " + id + " não existe.", HttpStatus.NOT_FOUND);
        }
        Stock stockToUpdate = stockRepository.findById(id).get();

        BeanUtils.copyProperties(stockDTO, stockToUpdate, this.getNullPropertyNames(stockDTO));

        this.stockRepository.save(stockToUpdate);

        return new ResponseEntity<>("Estoque atualizado com sucesso.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteStock(Long id) {
        Optional<Stock> optionalStock = stockRepository.findById(id);

        if (optionalStock.isPresent()) {
            stockRepository.deleteById(id);
            return new ResponseEntity<>("Estoque deletado com sucesso.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Estoque com ID " + id + " não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    private StockDTO stockToStockDTO(Stock stock) {
        return new StockDTO(stock);
    }

    public void updateStockByProduct(Long productId, int quantityOrdered) {
        Optional<Stock> optionalStock = findByProductId(productId);

        if (optionalStock.isPresent()) {
            Stock stock = optionalStock.get();
            int currentQuantityAvailable = stock.getQuantityAvailable();
            int updatedQuantityAvailable = currentQuantityAvailable - quantityOrdered;

            if (updatedQuantityAvailable >= 0) {
                stock.setQuantityAvailable(updatedQuantityAvailable);
                stockRepository.save(stock);
            } else {
                throw new IllegalArgumentException("Quantidade insuficiente em estoque para o produto com ID " + productId);
            }
        } else {
            throw new IllegalArgumentException("Produto com ID " + productId + " não encontrado em estoque");
        }
    }

    public Optional<Stock> findByProductId(Long productId) {
        return stockRepository.findByProductId(productId);
    }
}
