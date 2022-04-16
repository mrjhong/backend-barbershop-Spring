package com.barbershop.crud.security.entity;

import com.barbershop.crud.entity.Producto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;



@Entity
public class Perfil {
    @Id
    private int id;
    
    private String nombre;
    
    private String nombreUsuario;
    
    private String email;
    
    private String foto;
    
    @Column(length = 500)
    private String descripcion;
    
    private String telefono;
    
    private String facebook;
    
    private String instagram;
    
    private String whatsapp;
    
    
    @OneToOne(mappedBy = "perfilUsuario",cascade=CascadeType.ALL)
    private Usuario usuarioPrivate; 
    
    @OneToMany(mappedBy = "perfil",cascade=CascadeType.ALL)
    private List<Producto>productos;


    public Perfil() {
    }

    public Perfil(int id, String nombre, String nombreUsuario, String email, String foto, String descripcion, String telefono, String facebook, String instagram, String whatsapp) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.foto = foto;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.facebook = facebook;
        this.instagram = instagram;
        this.whatsapp = whatsapp;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }



    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

 

    
    
}