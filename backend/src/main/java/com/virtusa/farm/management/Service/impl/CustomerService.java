package com.virtusa.farm.management.Service.impl;

import com.virtusa.farm.management.DTO.Product;
import com.virtusa.farm.management.DTO.User;
import com.virtusa.farm.management.Dao.IProductRepository;
import com.virtusa.farm.management.Dao.IUserRepository;
import com.virtusa.farm.management.Service.ICustomerService;
import com.virtusa.farm.management.exception.ProductNotFoundException;
import com.virtusa.farm.management.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService implements ICustomerService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<User> allUsers() throws UserNotFoundException {
        return userRepository.findAll();
    }

    @Override
    public User getByEmailAndPassword(String email, String password) throws UserNotFoundException {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    @Override
    public User saveUser(User u) throws UserNotFoundException {
        return userRepository.save(u);
    }

    @Override
    public void deleteUser(User u) {
        userRepository.delete(u);
    }

    @Override
    public User updateUser(User u) {
//        if (userRepository.findByEmail(u.getEmail())!=null){

        log.info("User updated");
        return userRepository.save(u);

//        }else {
//            throw  new UserNotFoundException();
//        }
    }

    @Override
    public User addToCart(Long id, Product p) {
        User u = userRepository.findById(id).get();
        u.getCart().add(p);
        userRepository.save(u);
        return userRepository.findById(id).get();
    }

    @Override
    public List<Product> getCart(Long id) {
        return userRepository.findById(id).get().getCart();
    }

    @Override
    public List<Product> getCart(Long id, Product p) {
        if (userRepository.findById(id).get().getCart() == null) {
            log.info("no item is here");
            throw new UserNotFoundException();
        } else {
            return userRepository.findById(id).get().getCart();
        }

    }

    @Override
    public void removeFromCart(Long id, Product p) {
        User u = userRepository.findById(id).get();
        u.getCart().remove(p);
        userRepository.save(u);
    }

    @Override
    public void checkoutCart(Long id) {

    }

    @Override
    public void changeCartItemQuantity(Long id, Product p, Long quantity) {
        User u = userRepository.findById(id).get();
        for (Product product : u.getCart()) {
            if (product.getId() == p.getId()) {
                product.setQuantity(quantity);
            }
        }
        userRepository.save(u);
    }

    @Override
    public List<Product> viewCart(Long id) {
        return userRepository.findById(id).get().getCart();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product searchProduct(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Integer getTotal(Long id) {
        Long total = 0L;
        for (Product product : userRepository.findById(id).get().getCart()) {
            total += product.getPrice();
        }
        return Math.toIntExact(total);
    }

//    @Override
//    public List<Product> getByKeyword(String keyword) {
//        return productRepository.findByKeyword(keyword);
//    }
}
