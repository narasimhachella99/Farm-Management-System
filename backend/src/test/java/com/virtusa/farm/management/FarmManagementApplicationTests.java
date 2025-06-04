package com.virtusa.farm.management;

import com.virtusa.farm.management.DTO.Product;
import com.virtusa.farm.management.DTO.User;
import com.virtusa.farm.management.Service.IAdminService;
import com.virtusa.farm.management.Service.ICustomerService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FarmManagementApplicationTests {

}

//    @Autowired
//    private IAdminService productService;
//    @Autowired
//    private ICustomerService userService;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    @Order(1)
//    void checkProductService() {
//        assert productService != null;
//    }
//
//    @Test
//    @Order(2)
//    void checkUserService() {
//        assert userService != null;
//    }
//
//    @Test
//    @Order(3)
//    void addUser() {
//        User u = User.builder()
//                .name("name")
//                .email("email")
//                .password("password")
//                .build();
//        userService.saveUser(u);
//        assert u.getEmail().equals("email");
//    }
//
//    @Test
//    @Order(4)
//    void readUser() {
//        assert userService.getUserByEmail("email") != null;
//    }
//
//    @Test
//    @Order(5)
//    void updateUser() {
//        User u = userService.getUserByEmail("email");
//        assert u != null;
//        u.setEmail("abc@gmail.com");
//        userService.updateUser(u);
//        assert userService.getUserByEmail("abc@gmail.com") != null;
//    }
//
//
//    @Test
//    @Order(6)
//    void deleteUser() {
//        User u = userService.getUserByEmail("abc@gmail.com");
//        userService.deleteUser(u);
//        assert userService.getUserByEmail("abc@gmail.com") == null;
//    }
//
//    @Test
//    @Order(7)
//    void addProduct() {
//        Product p = Product.builder()
//                .name("name")
//                .category("category")
//                .build();
//        productService.saveProduct(p);
//        assert p.getCategory().equals("category");
//    }
//
//    @Test
//    @Order(8)
//    void readProduct() {
//        assert productService.getByCategory("category") != null;
//    }
//
//    @Test
//    @Order(9)
//    void updateProduct() {
//        List<Product> productList = productService.getByCategory("category");
//        Product p = productList.get(0);
//        assert p != null;
//        p.setCategory("vegetable");
//        productService.updateProduct(p);
//        assert productService.getByName("veg") != null;
//    }
//
//    @Test
//    @Order(10)
//    void deleteProduct() {
//        List<Product> products = productService.getByCategory("veg");
//        Product p = products.get(0);
//        productService.deleteProduct(p);
//        assert productService.getByCategory("veg").get(0) == null;
//    }
//}
