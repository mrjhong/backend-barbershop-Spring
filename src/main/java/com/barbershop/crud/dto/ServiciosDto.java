package com.barbershop.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ServiciosDto {
    
    @NotBlank
    private String nombre;
    @NotBlank
    private String descrtipcion;
    @NotBlank
    private String imagen;

    public ServiciosDto(String nombre, String descrtipcion, String imagen) {
        this.nombre = nombre;
        this.descrtipcion = descrtipcion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrtipcion() {
        return descrtipcion;
    }

    public void setDescrtipcion(String descrtipcion) {
        this.descrtipcion = descrtipcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
}
