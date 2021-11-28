package com.barbershop.crud.service;
import com.barbershop.crud.entity.Servicios;
import java.util.Optional;

public interface ServiciosService {
    public Servicios save (Servicios servicio);
    public Optional <Servicios> get(Integer id);
    public void  update (Servicios servicios);   
    public void delete(Integer id);
}
