package com.fmattaperdomo.inventoryapp.services;

import com.fmattaperdomo.inventoryapp.models.Product;
import com.fmattaperdomo.inventoryapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product searchProductById(Integer idProduct) {
        Product product =
                this.productRepository.findById(idProduct).orElse(null);
        return product;

    }

    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer idProduct) {
        this.productRepository.deleteById(idProduct);
    }
}
