package com.example.demo.controller;

import com.example.demo.dto.ArticuloDTO;
import com.example.demo.service.ArticuloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/inventario")
@RequiredArgsConstructor
public class ArticuloController {

    private final ArticuloService svc;
    private static final List<String> CATS =
        List.of("Electrónica", "Herramientas", "Oficina", "Limpieza");

    @GetMapping
    public String listar(@RequestParam(required = false) String cat,
                         @RequestParam(required = false) String q,
                         @RequestParam(required = false) String estado,
                         Model m) {
        m.addAttribute("articulos",  svc.listarConFiltros(cat, q, estado));
        m.addAttribute("categorias", CATS);
        m.addAttribute("catSel",     cat);
        m.addAttribute("q",          q);
        m.addAttribute("estadoSel",  estado);
        m.addAttribute("dto",        new ArticuloDTO());
        m.addAttribute("alertas",    svc.listarStockBajo().size() + svc.listarSinStock().size());
        return "inventario/inventario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("dto") ArticuloDTO dto,
                          BindingResult br, Model m, RedirectAttributes ra) {
        if (br.hasErrors()) { m.addAttribute("categorias", CATS); return "inventario/inventario"; }
        try {
            svc.guardar(dto);
            ra.addFlashAttribute("msg",  "Artículo guardado correctamente.");
            ra.addFlashAttribute("tipo", "success");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("msg",  e.getMessage());
            ra.addFlashAttribute("tipo", "danger");
        }
        return "redirect:/inventario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute("dto") ArticuloDTO dto,
                             BindingResult br, Model m, RedirectAttributes ra) {
        if (br.hasErrors()) { m.addAttribute("categorias", CATS); return "inventario/inventario"; }
        try {
            svc.actualizar(id, dto);
            ra.addFlashAttribute("msg",  "Artículo actualizado.");
            ra.addFlashAttribute("tipo", "success");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("msg",  e.getMessage());
            ra.addFlashAttribute("tipo", "danger");
        }
        return "redirect:/inventario";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        try {
            svc.eliminar(id);
            ra.addFlashAttribute("msg",  "Artículo eliminado.");
            ra.addFlashAttribute("tipo", "success");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("msg",  e.getMessage());
            ra.addFlashAttribute("tipo", "danger");
        }
        return "redirect:/inventario";
    }
}
