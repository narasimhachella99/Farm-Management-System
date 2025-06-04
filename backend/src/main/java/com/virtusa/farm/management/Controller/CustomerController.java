package com.virtusa.farm.management.Controller;

import com.virtusa.farm.management.DTO.Product;
import com.virtusa.farm.management.DTO.User;
import com.virtusa.farm.management.Service.IAdminService;
import com.virtusa.farm.management.Service.ICustomerService;
import com.virtusa.farm.management.exception.UserNotFoundException;
import com.virtusa.farm.management.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class CustomerController {

    private static final String ADMIN_LOGIN = "admin/login";
    private static final String ADMIN_HOME = "admin/home";
    private static final String USER_REGISTER = "user/register";
    private static final String USER_LOGIN = "user/login";
    private static final String USER_HOME = "user/home";
    private static final String ALL_USERS = "admin/allUsers";
    private static final String USERS = "user/allProducts";

    @Autowired
    private  ICustomerService userService;
    @Autowired
    private IAdminService productService;


    @Autowired
    private HttpServletRequest req;

    @GetMapping("/allUsers")
    private String allUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return ALL_USERS;
    }

    @GetMapping("/products")
    private String products(Model model) {
        model.addAttribute("products", productService.allProducts());
        return USERS;
    }

    @PostMapping("/login")
    private String adminLogin(Model model) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email.equals("") || password.equals("")) {
            model.addAttribute("error", "please fill all the fields");
        } else {
            if (email.equals("admin@gmail.com") && password.equals("admin")) {
                req.getSession().setAttribute("admin", "admin");
                return ADMIN_HOME;
            } else {
                model.addAttribute("error", "please give valid credentials");
            }
        }
        return ADMIN_LOGIN;
    }

    @PostMapping("/register")
    public String registerUser(Model model) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        if (email.equals("") || password.equals("") || name.equals("")) {
            model.addAttribute("error", "Please fill all the fields");
        } else {
            User user = new User(name, email, password, address);
            if (userService.getUserByEmail(user.getEmail()) != null) {
                model.addAttribute("error", "User already exists");
            } else {
                userService.saveUser(user);
                model.addAttribute("success", "Registration Successful");
            }
        }
        return USER_REGISTER;
    }

    @PostMapping("/userLogin")
    private String userLog(Model model) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email.equals("") || password.equals("")) {
            model.addAttribute("error", "please fill all the fields");
        } else {
            User user = userService.getByEmailAndPassword(email, password);
            if (user != null) {
                req.getSession().setAttribute("user", user);
                return USER_HOME;
            } else {
                return USER_LOGIN;
            }
        }
        return "/";
    }

    @GetMapping("/addToCart/{id}")
    private String addToCart(Long id) {
        User u = (User) req.getSession().getAttribute("user");
        Product p = productService.productGetById(id);
        userService.addToCart(u.getId(), p);
        return "redirect:/viewCart";
    }

    @DeleteMapping("/product/{id}/cart")
    private ResponseEntity<?> deleteCartItem(@RequestBody Product p, @PathVariable Long id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            userService.removeFromCart(id, p);
            res.put("msg", "item removed from cart");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("msg", "something goes wrong");
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("/cart/remove/{id}")
    private String removeCartItem(@PathVariable Long id) {
        User u = (User) req.getSession().getAttribute("user");
        Product p = productService.productGetById(id);
        userService.removeFromCart(u.getId(), p);
        userService.updateUser(u);
        return "redirect:/viewCart";
    }

    @PostMapping("/product/{id}/changeCartItemQuantity")
    private ResponseEntity<?> changeCartItemQuantity(@PathVariable Long id, @RequestBody Product p, @RequestBody Long quantity) {
        HashMap<String, String> res = new HashMap<>();
        try {
            userService.changeCartItemQuantity(id, p, quantity);
            res.put("msg", "Quantity is changed");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("msg", "not changed");
            return ResponseEntity.ok(res);
        }
    }

}
