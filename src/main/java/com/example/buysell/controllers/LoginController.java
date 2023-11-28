package com.example.buysell.controllers;

import com.example.buysell.models.Role;
import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepository;
import com.example.buysell.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class LoginController {
    private final UserService userService;

//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login";
//    }
//    @GetMapping("/regist")
//    public String reg(Model model){
//        model.addAttribute("user", new User());
//        return "register";
//    }
//
////    @GetMapping("/user/home")
////    @PreAuthorize("hasRole('USER')")
////    public String userHome() {
////        return "user/home";
////    }
////
////    @GetMapping("/admin/home")
////    @PreAuthorize("hasRole('ADMIN')")
////    public String adminHome() {
////        return "admin/home";
////    }
//
//    @GetMapping("/user/home")
//    @PreAuthorize("hasRole('USER')")
//    public String userHome() {
//        return "user/home";
//    }
//
//    @GetMapping("/admin/home")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String adminHome() {
//        return "admin/home";
//    }
//
//
//
////    @PostMapping("/regist")
////    public String reg(@ModelAttribute("user") User user , Model model) {
////        user.setRole(Role.USER);
////
////        try{
////            userService.save(user);
////            return "redirect:/login";
////        } catch (Exception e) {
////            model.addAttribute("alreadyExists", "Пользователь с таким логином уже существует");
////            return "register";
////        }
////    }
@GetMapping("/login")
public String login() {
    return "login";
}

    @GetMapping("/register")
    public String registration() {
        return "register";
    }


    @PostMapping("/register")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)){
            model.addAttribute("errorMassage", "Пользователь с email: " + user.getEmail() + "Пользователь уже существует ");
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }
}
