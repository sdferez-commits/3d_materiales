package com.example.demo.repository;

import com.example.demo.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    List<Articulo> findByActivoTrue();
    boolean existsByCodigo(String codigo);

    @Query("SELECT a FROM Articulo a WHERE a.activo = true " +
           "AND (:cat IS NULL OR a.categoria = :cat) " +
           "AND (:q IS NULL OR LOWER(a.nombre) LIKE LOWER(CONCAT('%',:q,'%')) " +
           "  OR LOWER(a.codigo) LIKE LOWER(CONCAT('%',:q,'%')))")
    List<Articulo> findByFiltros(@Param("cat") String cat, @Param("q") String q);

    @Query("SELECT a FROM Articulo a WHERE a.activo=true AND a.stock>0 AND a.stock<a.stockMinimo")
    List<Articulo> findStockBajo();

    @Query("SELECT a FROM Articulo a WHERE a.activo=true AND a.stock=0")
    List<Articulo> findSinStock();

    @Query("SELECT COUNT(a) FROM Articulo a WHERE a.activo=true") long countActivos();
    @Query("SELECT COUNT(a) FROM Articulo a WHERE a.activo=true AND a.stock>=a.stockMinimo") long countNormal();
    @Query("SELECT COUNT(a) FROM Articulo a WHERE a.activo=true AND a.stock>0 AND a.stock<a.stockMinimo") long countBajo();
    @Query("SELECT COUNT(a) FROM Articulo a WHERE a.activo=true AND a.stock=0") long countCritico();

    List<Articulo> findTop5ByActivoTrueOrderByIdDesc();
}
