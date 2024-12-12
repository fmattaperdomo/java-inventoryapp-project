package com.fmattaperdomo.inventoryapp.controllers;

import com.fmattaperdomo.inventoryapp.exceptions.ResourceNotFound;
import com.fmattaperdomo.inventoryapp.models.Product;
import com.fmattaperdomo.inventoryapp.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product productReceived){
        Product product = this.productService.searchProductById(id);
        if (product == null)
            throw new ResourceNotFound("id Not Found: " + id);
        product.setDescription(productReceived.getDescription());
        product.setPrice(productReceived.getPrice());
        product.setQuantity(productReceived.getQuantity());
        this.productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>>
    deleteProduct(@PathVariable int id){
        Product product = productService.searchProductById(id);
        if (product == null)
            throw new ResourceNotFound("id Not Found: " + id);
        this.productService.deleteProductById(product.getIdProduct());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
