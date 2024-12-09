package com.fmattaperdomo.inventoryapp.services;

import com.fmattaperdomo.inventoryapp.models.Product;

import java.util.List;

public interface IProductService {
    public List<Product> listProducts();

    public Product searchProductById(Integer idProduct);

    public void saveProduct(Product product);

    public void deleteProductById(Integer idProduct);

}
