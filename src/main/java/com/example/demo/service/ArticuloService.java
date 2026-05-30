package com.example.demo.service;

import com.example.demo.dto.ArticuloDTO;
import com.example.demo.model.Articulo;
import java.util.List;

public interface ArticuloService {
    List<Articulo> listarConFiltros(String cat, String q, String estado);
    Articulo buscarPorId(Long id);
    Articulo guardar(ArticuloDTO dto);
    Articulo actualizar(Long id, ArticuloDTO dto);
    void eliminar(Long id);
    List<Articulo> listarStockBajo();
    List<Articulo> listarSinStock();
    List<Articulo> listarUltimos5();
    long[] estadisticas();
}
