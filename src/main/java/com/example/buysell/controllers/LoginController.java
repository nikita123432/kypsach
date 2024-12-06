package com.example.buysell.controllers;
import com.example.buysell.models.Role;
import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepository;
import com.example.buysell.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    @GetMapping("/regist")
    public String reg(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Выполните здесь необходимые действия при выходе пользователя

        // Инвалидируем сессию
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        // Редиректим пользователя на страницу логина или куда угодно
        return "redirect:/login";
    }


    @PostMapping("/regist")
    public String reg(@ModelAttribute("user") User user , Model model) {
        user.setRole(Role.ROLE_USER);
        try{
            userService.saveUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("alreadyExists", "Пользователь с таким логином уже существует");
            return "register";
        }
    }
}

