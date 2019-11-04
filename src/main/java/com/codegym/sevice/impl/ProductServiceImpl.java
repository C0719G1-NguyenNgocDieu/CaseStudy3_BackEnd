package com.codegym.sevice.impl;

import com.codegym.model.Product;
import com.codegym.repository.ProductRepository;
import com.codegym.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remote(Long id) {
productRepository.deleteById(id);
    }
}
