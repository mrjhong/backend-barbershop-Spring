package com.barbershop.crud.security.dto;


public class UsuarioProducto {

    private String nombreUsuario;
    private String foto;

    public UsuarioProducto(String nombreUsuario, String foto) {
        this.nombreUsuario = nombreUsuario;
        this.foto = foto;
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


    

}
