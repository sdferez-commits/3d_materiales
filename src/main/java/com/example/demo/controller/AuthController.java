package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error,
                        @RequestParam(required = false) String logout,
                        Model model) {
        if (error  != null) model.addAttribute("error",  "Usuario o contraseña incorrectos.");
        if (logout != null) model.addAttribute("logout", "Sesión cerrada correctamente.");
        return "auth/login";
    }
}
