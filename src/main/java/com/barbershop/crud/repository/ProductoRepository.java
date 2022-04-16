package com.barbershop.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.crud.entity.Producto;
import java.util.List;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByNombre(String nombre);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByPerfilId(int perfil_id);
    boolean existsByNombre(String nombre);
}
