package com.barbershop.crud.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.barbershop.crud.security.entity.Usuario;

@Entity

public class Servicios {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idServicios;
    private String nombre;
    private String descripcion;
    private String imagen;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id")
    private Usuario idUser;
    
    public Servicios() {
    }	

   


	public Servicios(int idServicios, String nombre, String descripcion, String imagen, Usuario idUser) {
		super();
		this.idServicios = idServicios;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.idUser = idUser;
	}




	public int getIdServicios() {
		return idServicios;
	}




	public void setIdServicios(int idServicios) {
		this.idServicios = idServicios;
	}




	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}




	@Override
	public String toString() {
		return "Servicios [idServicios=" + idServicios + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", imagen=" + imagen + ", idUser=" + idUser + "]";
	}


	
}

