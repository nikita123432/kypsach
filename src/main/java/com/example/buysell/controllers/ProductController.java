package com.example.buysell.controllers;
import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.repositories.ProductRepository;
import com.example.buysell.services.ProductService;
import com.example.buysell.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller

public class ProductController {
    private final ProductService productService;
    @Autowired
    UserService userService;


@Autowired
public ProductController(ProductService productService) {
    this.productService = productService;
}

    @GetMapping("/")
    public String products() {
        return "products";
    }

    @GetMapping("/sex")
    public String sex(@RequestParam("sex") String sex,
                      @RequestParam(name = "title", required = false, defaultValue = "default") String title,
                    @RequestParam(name = "category", required = false, defaultValue = "default") String category, Model model){
        List<Product> products = productService.findBySex(sex);
        if(!title.equals("default")){
            products = productService.findBySexAndTitle(products,title);
        }
        if(!category.equals("default")){
            products = productService.filterByCategory(products,category);
        }
        model.addAttribute("products", products);
        return "sex";
    }

    @PostMapping("/product/basket")
    public String basketProduct(@RequestParam("productId") Long id, RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        List<Product> products = user.getBasket();
        products.add(productService.getProductById(id));
        user.setBasket(products);
        try {
            userService.save(user);
        } catch (Exception e) {
            System.out.println("");
        }
        redirectAttributes.addAttribute("productId",id);
        return "redirect:/product";
    }

    @GetMapping("/basket")
    public String basket(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("products",user.getBasket());
        return "basket";
    }

    @GetMapping("/product")
    public String product(@RequestParam("productId") Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "product";
    }
    @PostMapping("/pay")
    public String pay(RedirectAttributes redirectAttributes, @RequestParam("productId") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        List<Product> basket = user.getBasket();
        basket.remove(productService.getProductById(id));
        user.setBasket(basket);
        userService.save(user);
        redirectAttributes.addFlashAttribute("thanks","Спасибо за покупку");
        return "redirect:/basket";
    }
    @GetMapping("/products")
    public String showProductsPage() {
        return "products"; // Вернуть имя вашего Thymeleaf шаблона для страницы с товарами
    }

    @GetMapping("/clothing")
    public String showClothingCatalog() {
        return "clothing_catalog"; // Название представления (HTML-шаблона)
    }

}
