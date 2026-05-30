package com.example.demo.controller;

import com.example.demo.service.ArticuloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class InicioController {

    private final ArticuloService svc;

    @GetMapping
    public String inicio(Model m) {
        long[] st = svc.estadisticas();
        m.addAttribute("total",   st[0]);
        m.addAttribute("ok",      st[1]);
        m.addAttribute("bajo",    st[2]);
        m.addAttribute("crit",    st[3]);
        m.addAttribute("ultimos", svc.listarUltimos5());
        m.addAttribute("alertas", st[2] + st[3]);
        return "inicio";
    }
}
