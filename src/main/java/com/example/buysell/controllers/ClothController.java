package com.example.buysell.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClothController {
    @GetMapping("/man")
    public String manMain(Model model){
        return "man-main";
    }
}
