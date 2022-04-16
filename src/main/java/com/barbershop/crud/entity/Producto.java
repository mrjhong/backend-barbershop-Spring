package com.barbershop.crud.entity;


import com.barbershop.crud.security.entity.Perfil;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String precio;
    private String foto;
    private String descripcion;
    private String categoria;
    
    @ManyToOne()
    @JoinColumn(name = "perfil_id")
    @NotNull
    private Perfil perfil;

    public Producto() {
    }

    public Producto(String nombre, String precio, String foto, String descripcion, String categoria, Perfil perfil) {
        this.nombre = nombre;
        this.precio = precio;
        this.foto = foto;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.perfil = perfil;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

  
    
}
