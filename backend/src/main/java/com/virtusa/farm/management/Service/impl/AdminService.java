package com.virtusa.farm.management.Service.impl;

import com.virtusa.farm.management.DTO.Product;
import com.virtusa.farm.management.DTO.User;
import com.virtusa.farm.management.Dao.IProductRepository;
import com.virtusa.farm.management.Dao.IUserRepository;
import com.virtusa.farm.management.Service.IAdminService;
import com.virtusa.farm.management.exception.ProductNotFoundException;
import com.virtusa.farm.management.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminService implements IAdminService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IUserRepository userRepository;


    @Override
    public List<Product> allProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product productGetById(Long id) throws ProductNotFoundException {
        if (productRepository.findById(id) == null) {
            log.info("Category not found");
            throw new ProductNotFoundException();
        } else {
            return productRepository.findById(id).get();
        }
    }

    @Override
    public void postProduct(Product p) throws ProductNotFoundException {

        if (productRepository.findById(p.getId()).get() == null) {
            log.info("product not found");
            throw new ProductNotFoundException();
        } else {
            productRepository.save(p);
        }
    }

    @Override
    public Product updateProduct(Product p) {
        if (productRepository.findById(p.getId()).get() != null) {
            return productRepository.save(p);
        } else {
            log.info("product not found");
            throw new ProductNotFoundException();
        }
    }

    @Override
    public List<User> getAllUsers() throws UserNotFoundException {
        return userRepository.findAll();
    }

    @Override
    public List<Product> getAllOrders() throws UserNotFoundException {
        List<User> users = userRepository.findAll();
        List<Product> orders = new ArrayList<>();
        for (User u : users) {
            orders.addAll(u.getCart());
        }
        return orders;
    }

    @Override
    public List<Product> getProductByCategory(String category) throws ProductNotFoundException {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product addProduct(Product p) throws ProductNotFoundException {
        return productRepository.save(p);
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {

        if (productRepository.findById(id).get() == null) {
            log.info("product not found");
            throw new ProductNotFoundException();
        } else {
            productRepository.deleteById(id);
        }
    }

    @Override
    public void saveProduct(Product p) throws ProductNotFoundException {

        log.info("product not found");

        productRepository.save(p);
    }

    @Override
    public Product getByName(String name) throws ProductNotFoundException {
        if (productRepository.findByName(name).getName() != null) {
            return productRepository.findByName(name);
        } else {
            log.info("Name not found");
            throw new ProductNotFoundException();
        }
    }

    @Override
    public void deleteProduct(Product p) throws ProductNotFoundException {
        if (productRepository.findByName("name") == null) {
            log.info("Product not found");
            throw new UserNotFoundException();
        }
        productRepository.delete(p);
    }


    @Override
    public List<Product> getByCategory(String category) throws ProductNotFoundException {
        if (productRepository.findByCategory(category) == null) {
            log.info("Category not found");
            throw new ProductNotFoundException();
        } else {
            return productRepository.findByCategory(category);
        }
    }
}
