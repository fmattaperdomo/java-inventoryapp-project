package com.fmattaperdomo.inventoryapp.controllers;

import com.fmattaperdomo.inventoryapp.exceptions.ResourceNotFound;
import com.fmattaperdomo.inventoryapp.models.Product;
import com.fmattaperdomo.inventoryapp.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
//http://locahost:8080/inventaryapp
@RequestMapping("inventoryapp")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {
    private static final Logger logger =
            LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    //http://locahost:8080/inventaryapp/products
    @GetMapping("/products")
    public List<Product> getProducts(){
        List<Product> products = this.productService.listProducts();
        logger.info("Get Products:");
        products.forEach((product -> logger.info(product.toString())));
        return products;
    }
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        logger.info("Add Product : " + product);
        return this.productService.saveProduct(product);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable int id){
        Product product =
                this.productService.searchProductById(id);
        if(product != null)
            return ResponseEntity.ok(product);
        else
            throw new ResourceNotFound("The Id was not found: " + id);
    }

}
