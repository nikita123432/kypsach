//package com.example.buysell.controllers;
//
//import ch.qos.logback.core.model.Model;
//import com.example.buysell.registretion.User;
//import com.example.buysell.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class RegistrationController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//        return "registration";
//    }
//
//    @PostMapping("/register")
//    public String processRegistration(@ModelAttribute("user") User user) {
//        // Добавьте код для сохранения пользователя в базе данных
//        userRepository.save(user);
//        return "redirect:/login";
//    }
//
//}
