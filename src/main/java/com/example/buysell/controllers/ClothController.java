//package com.example.buysell.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class ClothController {
//
//    @GetMapping("/cloths")
//    public String showCloths() {
//        // Логика для отображения списка одежды
//        return "cloths";
//    }
//
//    @GetMapping("/addCloth")
//    public String showAddClothForm(Model model) {
//        model.addAttribute("cloth", new Cloth());
//        return "addCloth";
//    }
//
//    @PostMapping("/addCloth")
//    public String addCloth(@ModelAttribute Cloth cloth) {
//        clothRepository.save(cloth);
//        return "redirect:/cloths";
//    }
//
//    @GetMapping("/manCloths")
//    public String manMain(Model model) {
//        // Логика для отображения одежды для мужчин
//        return "manCloths";
//    }
//}
