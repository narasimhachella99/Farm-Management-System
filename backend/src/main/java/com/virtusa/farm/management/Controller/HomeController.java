package com.virtusa.farm.management.Controller;

import com.virtusa.farm.management.DTO.User;
import com.virtusa.farm.management.Dao.IProductRepository;
import com.virtusa.farm.management.Service.IAdminService;
import com.virtusa.farm.management.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
    private static final String ADMIN_LOGIN = "admin/login";
    private static final String USER_REGISTER = "user/register";
    private static final String USER_LOGIN = "user/login";
    private static final String VIEW_CART = "user/cart";
    private static final String ALL_ORDERS = "admin/orders";

    @Autowired
    private ICustomerService userService;

    @Autowired
    private IAdminService productService;
    @Autowired
    private HttpServletRequest req;

    @GetMapping("/")
    private String index() {
        req.getSession().invalidate();
        return "index";
    }

    @GetMapping("/adminLogin")
    private String adminLogin() {
        return ADMIN_LOGIN;
    }

    @GetMapping("/userReg")
    private String userReg() {
        return USER_REGISTER;
    }

    @GetMapping("/userLogin")
    private String userLogin() {
        return USER_LOGIN;
    }

    @GetMapping("/viewCart")
    private String viewCart(Model model) {
        User user = (User) req.getSession().getAttribute("user");
        model.addAttribute("products", userService.getCart(user.getId()));
        return VIEW_CART;
    }

    @GetMapping("/allOrders")
    private String allOrders(Model model) {
        model.addAttribute("products", productService.getAllOrders());
        return ALL_ORDERS;
    }

    @GetMapping("/buy")
    private String buy(Model model) {
        User u = (User) req.getSession().getAttribute("user");
        model.addAttribute("total", userService.getTotal(u.getId()));
        u.getCart().clear();
        userService.updateUser(u);
        return "user/buy";
    }

}
