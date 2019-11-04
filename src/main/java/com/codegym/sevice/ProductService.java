package com.codegym.sevice;

import com.codegym.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    void remote(Long id);
}
