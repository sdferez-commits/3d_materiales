package com.example.demo.service;

import com.example.demo.dto.ArticuloDTO;
import com.example.demo.model.Articulo;
import com.example.demo.repository.ArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticuloServiceImpl implements ArticuloService {

    private final ArticuloRepository repo;

    @Override @Transactional(readOnly = true)
    public List<Articulo> listarConFiltros(String cat, String q, String estado) {
        List<Articulo> list = repo.findByFiltros(
            (cat != null && !cat.isBlank()) ? cat : null,
            (q   != null && !q.isBlank())   ? q   : null
        );
        if (estado != null && !estado.isBlank())
            list = list.stream()
                .filter(a -> a.getEstadoStock().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
        return list;
    }

    @Override @Transactional(readOnly = true)
    public Articulo buscarPorId(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Artículo no encontrado: " + id));
    }

    @Override
    public Articulo guardar(ArticuloDTO dto) {
        if (repo.existsByCodigo(dto.getCodigo()))
            throw new RuntimeException("El código ya existe: " + dto.getCodigo());
        return repo.save(toEntity(dto));
    }

    @Override
    public Articulo actualizar(Long id, ArticuloDTO dto) {
        Articulo a = buscarPorId(id);
        if (!a.getCodigo().equals(dto.getCodigo()) && repo.existsByCodigo(dto.getCodigo()))
            throw new RuntimeException("El código ya existe: " + dto.getCodigo());
        a.setCodigo(dto.getCodigo());
        a.setNombre(dto.getNombre());
        a.setCategoria(dto.getCategoria());
        a.setStock(dto.getStock());
        a.setStockMinimo(dto.getStockMinimo());
        a.setUbicacion(dto.getUbicacion());
        return repo.save(a);
    }

    @Override
    public void eliminar(Long id) {
        Articulo a = buscarPorId(id);
        a.setActivo(false);
        repo.save(a);
    }

    @Override @Transactional(readOnly = true)
    public List<Articulo> listarStockBajo() { return repo.findStockBajo(); }

    @Override @Transactional(readOnly = true)
    public List<Articulo> listarSinStock()  { return repo.findSinStock(); }

    @Override @Transactional(readOnly = true)
    public List<Articulo> listarUltimos5()  { return repo.findTop5ByActivoTrueOrderByIdDesc(); }

    @Override @Transactional(readOnly = true)
    public long[] estadisticas() {
        return new long[]{
            repo.countActivos(),
            repo.countNormal(),
            repo.countBajo(),
            repo.countCritico()
        };
    }

    private Articulo toEntity(ArticuloDTO d) {
        return Articulo.builder()
            .codigo(d.getCodigo()).nombre(d.getNombre())
            .categoria(d.getCategoria()).stock(d.getStock())
            .stockMinimo(d.getStockMinimo()).ubicacion(d.getUbicacion())
            .activo(true).build();
    }
}
