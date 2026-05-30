package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hacer-inventario")
public class HacerInventarioController {

    @GetMapping
    public String hacerInventario() {
        return "inventario/hacer-inventario";
    }
}
