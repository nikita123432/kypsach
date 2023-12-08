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

@Controller
@AllArgsConstructor
public class LoginController {
    UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {

        return "login";
    }
    //    @GetMapping("/user/home")
//    @PreAuthorize("hasRole('USER')")
//    public String userHome() {
//        return "user/home";
//    }
//    @PostMapping("/login")
//    public String showLoginForm(@ModelAttribute("user") User user) {
//        if ("ROLE_USER".equals(user.getRole())){
//            return "redirect:/admin";
//        }else return "redirect:/";
//
//    }
//
//    @GetMapping("/admin/home")
//    public String adminHome(Model model) {
//        return "admin/home";
//    }
    @GetMapping("/regist")
    public String reg(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/regist")
    public String reg(@ModelAttribute("user") User user , Model model) {
        user.setRole(Role.ROLE_USER);
        try{
            userService.save(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("alreadyExists", "Пользователь с таким логином уже существует");
            return "register";
        }
    }
}

