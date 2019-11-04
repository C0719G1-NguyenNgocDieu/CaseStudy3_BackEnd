package com.codegym;

import com.codegym.sevice.ProductService;
import com.codegym.sevice.impl.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }
}
