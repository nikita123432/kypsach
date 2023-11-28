package com.example.buysell.controllers;
import com.example.buysell.models.Product;
import com.example.buysell.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller

public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name ="title",required = false) String title, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        return "products";
    }
@Autowired
public ProductController(ProductService productService) {
    this.productService = productService;
}

    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return
                "addProduct";
    }
    @GetMapping("/user")
    public String user(){
        return "Hey user ";
    }

    @GetMapping("/admin")
    public String admin(){
        return "hey admin ";
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

//    @GetMapping("/man")
//    public String showMenCatalog() {
//        return "man"; // Название представления (HTML-шаблона)
//    }


    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-info";
    }
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }


//    @PostMapping("/product/create")
//    public String createProduct(Product product) {
//        productService.saveProduct(product);
//        return "redirect:/";
//    }


}
