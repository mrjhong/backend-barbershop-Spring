
package com.barbershop.crud.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.barbershop.crud.security.entity.Perfil;
import java.util.Optional;

@Repository       
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    Optional<Perfil> findByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
    boolean existsById(int id);
}

