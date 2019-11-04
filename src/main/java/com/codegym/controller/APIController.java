package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@CrossOrigin("*")
public class APIController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listAllProduct() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    //-------------------Retrieve Single Customer--------------------------------------------------------

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getCustomer(@PathVariable("id") long id) {
//        System.out.println("Fetching Customer with id " + id);
        Product product = productService.findById(id);
        if (product == null) {
//            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    //-------------------Create a Customer--------------------------------------------------------

    @RequestMapping(value = "/products/", method = RequestMethod.POST)
    public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
//        System.out.println("Creating Customer " + customer.getLastName());
        productService.save(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Customer --------------------------------------------------------

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
//        System.out.println("Updating Customer " + id);

        Product currentProduct = productService.findById(id);

        if (currentProduct == null) {
//            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setImage(product.getImage());

        productService.save(currentProduct);
        return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
    }

    //------------------- Delete a Customer --------------------------------------------------------

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
//        System.out.println("Fetching & Deleting Customer with id " + id);

        Product product = productService.findById(id);
        if (product == null) {
//            System.out.println("Unable to delete. Customer with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        productService.remote(id);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
}
