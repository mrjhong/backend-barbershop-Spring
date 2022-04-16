
package com.barbershop.crud.security.dto;


public class PerfilDto {
    
    private Integer id;
    private String nombreUsuario;
    private String foto;
    private String email;
    private String telefono;
    private String descripcion;
    private String instagram;
    private String facebook;
    private String whatsapp; 

    public PerfilDto(Integer id, String nombreUsuario, String foto, String email, String telefono, String descripcion, String instagram, String facebook, String whatsapp) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.foto = foto;
        this.email = email;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.instagram = instagram;
        this.facebook = facebook;
        this.whatsapp = whatsapp;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

 

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
  
   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

 
 

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
    
}


