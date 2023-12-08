package com.example.buysell.controllers;

import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.repositories.ProductRepository;
import com.example.buysell.services.ProductService;
import jdk.jfr.MetadataDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    ProductService productService;
    @GetMapping("/admin/home")
    public String adminHome() {
        // Возвращает страницу для администратора
        return "admin/home";
    }
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/products"; // перенаправление на страницу со списком продуктов
    }

    @PostMapping("/product/delete")
    public String deleteProduct(@RequestParam("productId") Long id, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("sex",productService.getProductById(id).getSex());
        productService.deleteProduct(id);
        return "redirect:/sex";
    }
    @GetMapping("/product/edit")
    public String productEdit(@RequestParam("productId") Long id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "editProduct";
    }
    @PostMapping("/product/edit")
    public String productEdit(@ModelAttribute("product") Product product, @RequestParam("productId") Long id,
                              @RequestParam("sex") String sex,
                              RedirectAttributes redirectAttributes){
        product.setId(id);
        product.setSex(sex);
        productService.saveProduct(product);
        redirectAttributes.addAttribute("productId", id);
        return "redirect:/product/edit";
    }
    @PostMapping("/product/add")
    public String productAdd(@ModelAttribute("product") Product product, @RequestParam("photos") MultipartFile[] files, @RequestParam("sex") String sex){
        product.setSex(sex);
        List<String> photoUrls = new ArrayList<>();
        for(MultipartFile file: files){
            if(!file.isEmpty()){
                try{
                    String filename = file.getOriginalFilename();
                    File up = new File("D:\\kursach\\buysell\\src\\main\\resources\\static\\photos\\",filename);
                    file.transferTo(up);
                    photoUrls.add(filename);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        product.setPhotoUrl(photoUrls);
        productService.saveProduct(product);
        return "addProduct";
    }
}
