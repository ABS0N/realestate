package hu.kiralybalazs.realestate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Ez azonnal átirányítja a főoldalra érkezőket az ingatlanok listájára
        return "redirect:/properties";
    }
}