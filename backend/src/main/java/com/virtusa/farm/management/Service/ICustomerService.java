package com.virtusa.farm.management.Service;

import com.virtusa.farm.management.DTO.Product;
import com.virtusa.farm.management.DTO.User;
import com.virtusa.farm.management.exception.ProductNotFoundException;
import com.virtusa.farm.management.exception.UserNotFoundException;

import java.util.List;

public interface ICustomerService {

    List<User> allUsers() throws RuntimeException;

    User getByEmailAndPassword(String email, String password) throws RuntimeException;

    User getUserByEmail(String email) throws UserNotFoundException;

    User saveUser(User u) throws UserNotFoundException;

    void deleteUser(User u) throws UserNotFoundException;

    User updateUser(User u) throws UserNotFoundException;

    User addToCart(Long id, Product p) throws UserNotFoundException;

    List<Product> getCart(Long id) throws UserNotFoundException;

    List<Product> getCart(Long id, Product p) throws UserNotFoundException;

    void removeFromCart(Long id, Product p) throws UserNotFoundException;

    void checkoutCart(Long id) throws UserNotFoundException;

    void changeCartItemQuantity(Long id, Product p, Long quantity) throws ProductNotFoundException;

    List<Product> viewCart(Long id) throws UserNotFoundException;

    List<Product> getAllProducts() throws ProductNotFoundException;

    Product searchProduct(String name) throws ProductNotFoundException;

    Integer getTotal(Long id);

//    List<Product> getByKeyword(String keyword);

}
