package com.barbershop.crud.service;

import com.barbershop.crud.entity.Servicios;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barbershop.crud.repository.ServiciosRepository;

@Service
public class ServiciosImpl implements ServiciosService {

    @Autowired
    private ServiciosRepository servicioRepository;

    @Override
    public Servicios save(Servicios servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Optional<Servicios> get(Integer id) {
        return servicioRepository.findById(id);  
    }

    @Override
    public void update(Servicios servicios) {
        servicioRepository.save(servicios);
    }

    @Override
    public void delete(Integer id) {
        servicioRepository.deleteById(id);
    }

   

}
