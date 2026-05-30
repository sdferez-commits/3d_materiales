package com.example.demo.controller;

import com.example.demo.service.ArticuloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stock-bajo")
@RequiredArgsConstructor
public class StockBajoController {

    private final ArticuloService svc;

    @GetMapping
    public String stockBajo(Model m) {
        m.addAttribute("sinStock",  svc.listarSinStock());
        m.addAttribute("stockBajo", svc.listarStockBajo());
        m.addAttribute("alertas",   svc.listarSinStock().size() + svc.listarStockBajo().size());
        return "inventario/stock-bajo";
    }
}
