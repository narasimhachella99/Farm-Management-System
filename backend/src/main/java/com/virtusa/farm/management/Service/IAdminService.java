package com.virtusa.farm.management.Service;

import com.virtusa.farm.management.DTO.Product;
import com.virtusa.farm.management.DTO.User;
import com.virtusa.farm.management.exception.ProductNotFoundException;
import com.virtusa.farm.management.exception.UserNotFoundException;

import java.util.List;

public interface IAdminService {
    Product addProduct(Product p) throws ProductNotFoundException;

    void deleteProduct(Long id) throws ProductNotFoundException;

    List<Product> allProducts() ;

    Product productGetById(Long id) throws ProductNotFoundException;

    void postProduct(Product p) throws ProductNotFoundException;

    Product updateProduct(Product p) throws ProductNotFoundException;

    List<User> getAllUsers() throws UserNotFoundException;

    List<Product> getAllOrders() throws UserNotFoundException;

    List<Product> getProductByCategory(String category) throws ProductNotFoundException;

    void saveProduct(Product p) throws ProductNotFoundException;

    Product getByName(String name) throws ProductNotFoundException;

    void deleteProduct(Product p) throws ProductNotFoundException;

    List<Product> getByCategory(String category) throws ProductNotFoundException;
}
