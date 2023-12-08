package com.example.buysell.controllers;
import com.example.buysell.models.Product;
import com.example.buysell.repositories.ProductRepository;
import com.example.buysell.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller

public class ProductController {
    private final ProductService productService;
//    @Autowired
//    private ProductRepository productRepository;
//
//
//    @GetMapping("/addProduct")
//    public String showAddProductForm(Model model) {
//        model.addAttribute("product", new Product());
//        return "addProduct";
//    }
//
//    @PostMapping("/addProduct")
//    public String addProduct(@ModelAttribute Product product) {
//        productRepository.save(product);
//        return "redirect:/products"; // перенаправление на страницу со списком продуктов
//    }


@Autowired
public ProductController(ProductService productService) {
    this.productService = productService;
}





    @GetMapping("/user")
    public String user(){
        return "Hey user ";
    }

//    @GetMapping("/admin")
//    public String admin(Model model){
//        model.addAttribute("yourAttribute", "hey admin");
//
//    return "admin";
//    }
    @GetMapping("/")
    public String products() {
        return "products";
    }

    @GetMapping("/sex")
    public String sex(@RequestParam("sex") String sex, Model model){
        List<Product> products = productService.findBySex(sex);
        model.addAttribute("products", products);
        return "sex";
    }

    @GetMapping("/product")
    public String product(@RequestParam("productId") Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

//    @PostMapping("/addProduct")
//    public String addProduct(Product product) {
//        productService.saveProduct(product);
//        return "redirect:/man"; // Перенаправление на вашу главную страницу
//    }

    @GetMapping("/clothing")
    public String showClothingCatalog() {
        return "clothing_catalog"; // Название представления (HTML-шаблона)
    }

    @GetMapping("/man")
    public String showMenCatalog() {
        return "man"; // Название представления (HTML-шаблона)
    }


    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-info";
    }



//    @PostMapping("/admin")
//    public String addProduct(@ModelAttribute("product") Product product) {
//        productService.saveProduct(product);
//        return "redirect:/admin/home";
//    }


//    @PostMapping("/product/create")
//    public String createProduct(Product product) {
//        productService.saveProduct(product);
//        return "redirect:/";
//    }


}
