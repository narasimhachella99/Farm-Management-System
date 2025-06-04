package com.virtusa.farm.management.Controller;

import com.virtusa.farm.management.DTO.Product;
import com.virtusa.farm.management.Service.IAdminService;
import com.virtusa.farm.management.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class ProductController {
    private static final String ADD_PRODUCT = "admin/addProduct";
    private static final String ALL_PRODUCTS = "admin/allProducts";
    private static final String EDIT_PRODUCT = "admin/editProduct";
    @Autowired
    private IAdminService productService;

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private HttpServletRequest req;

    @GetMapping("/allProducts")
    private String allProducts(Model model){
       model.addAttribute("products",productService.allProducts());
        return ALL_PRODUCTS;
    }

//    @GetMapping("/products")
//    private String products( @RequestParam(required = false) String keyword, Model model){
//        if (keyword!=null){
//            model.addAttribute("products",customerService.getByKeyword(keyword));
//        }else {
//            model.addAttribute("products",customerService.getAllProducts());
//        }
//        return "user/allProducts";
//    }

    @GetMapping("/products/{id}")
    private ResponseEntity<?> getById(@PathVariable Long id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(productService.productGetById(id), HttpStatus.OK);
        } catch (Exception e) {
            res.put("error", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/editProduct/{id}")
    private String editProduct(@PathVariable Long id, Model model) {
        if (req.getSession().getAttribute("admin") != null) {
            model.addAttribute("product", productService.productGetById(id));
            return EDIT_PRODUCT;
        }
        return "/";
    }
    @GetMapping("/deleteProduct/{id}")
    private String deleteProduct(@PathVariable Long id) {
            productService.deleteProduct(id);
            return ALL_PRODUCTS;
    }

    @PostMapping("/editProduct/{id}")
    private String editProduct(@PathVariable Long id) {
        if (req.getSession().getAttribute("admin") != null) {
            Product product = productService.productGetById(id);
            String name = req.getParameter("name");
            Long quantity=Long.valueOf(req.getParameter("quantity"));
            String category= req.getParameter("category");
            Long price = Long.valueOf(req.getParameter("price"));
            product.setName(name);
           product.setQuantity(quantity);
           product.setCategory(category);
           product.setPrice(price);
            productService.updateProduct(product);
            return "redirect:/allProducts";
        }
        return "redirect:/";
    }


    @PostMapping("/addProduct")
    private String addProduct(Model model) {
        String name=req.getParameter("name");
        Long quantity= Long.valueOf(req.getParameter("quantity"));
        String category=req.getParameter("category");
        Long price=Long.valueOf(req.getParameter("price"));
        Product p=new Product(name,quantity,category,price);
        if (p!=null){
            model.addAttribute("success","added product successfully");
            productService.saveProduct(p);
            return ADD_PRODUCT;
        }else {
            model.addAttribute("error","something went wrong!!!");
            return ADD_PRODUCT;
        }
    }

//    @PostMapping("/addToCart")
//    private String addToCart(Model model){
//        String name=req.getParameter("name");
//        String
//    }



    @GetMapping("/products/category/{category}")
    private ResponseEntity<?> getCategory(@PathVariable String category) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(productService.getByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            res.put("error", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/addProduct")
    private String addProducts(){
        return ADD_PRODUCT;
    }

    @GetMapping("/viewProduct")
    private String viewProduct(){
        return ALL_PRODUCTS;
    }


}
