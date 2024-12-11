package com.fmattaperdomo.inventoryapp.controllers;

import com.fmattaperdomo.inventoryapp.models.Product;
import com.fmattaperdomo.inventoryapp.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
