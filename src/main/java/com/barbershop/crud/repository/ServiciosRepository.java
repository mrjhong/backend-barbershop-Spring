package com.barbershop.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.barbershop.crud.entity.Servicios;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Integer>
{
    
}
