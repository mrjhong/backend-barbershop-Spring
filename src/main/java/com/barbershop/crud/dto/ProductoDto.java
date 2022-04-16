package com.barbershop.crud.dto;

import com.barbershop.crud.security.entity.Usuario;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductoDto {

    @NotBlank
    private String nombre;
    @Min(0)
    private String precio;
    @NotBlank
    private String foto;
    @NotBlank
    private String descripcion;
    
    @NotBlank
    private Integer usuario;

    public ProductoDto() {
    }

    public ProductoDto(@NotBlank String nombre, @Min(0) String precio ,@NotBlank String foto, @NotBlank String descripcion,Integer usuario) {
        this.nombre = nombre;
        this.precio = precio;
        this.foto = foto;
        this.descripcion = descripcion;
        this.usuario = usuario;

    }
 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

  
    
}
