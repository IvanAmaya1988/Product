package com.cda.Product.repository;

import com.cda.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //Visto en clase
    List<Product> findByNameContainingIgnoreCaseAndCategory(String name, String category);
    //Visto en clase
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',?1,'%')) AND p.category = ?2 " +
    " ORDER BY p.price ASC")
    List<Product> findProductsConJPQL(String name,String category);
    //Visto en clase
    @Query(value = "SELECT * FROM PRODUCTS WHERE NOMBRE= ?1", nativeQuery = true)
    List<Product> findProductsConSQL(String name,String category);

    // 1. Buscar productos por nombre, ignorando mayusculas y minusculas.
    List<Product> findByNameContainingIgnoreCase(String name);

    // 2. Buscar productos por categoria exacta.
    List<Product> findByCategory(String category);

    // 3. Buscar productos por rango de precios.
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    // 4. Buscar productos por categoria y devolverlos ordenados por precio descendente.
    @Query(value = "SELECT p FROM Product p WHERE p.category = :category ORDER BY p.price DESC")
    List<Product> findByCategoryOrderByPriceDesc(@Param("category") String category);

    // 5. Buscar productos por nombre usando un patrón con comodines.
    @Query(value = "SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchByName(@Param("name") String name);

    // 6. Conteo de productos por categoría
    Long countByCategory(String category);
}
