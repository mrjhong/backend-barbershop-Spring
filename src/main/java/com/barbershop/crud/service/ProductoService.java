package com.barbershop.crud.service;

import com.barbershop.crud.dto.ProductoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbershop.crud.entity.Producto;
import com.barbershop.crud.repository.ProductoRepository;
import com.barbershop.crud.security.entity.Perfil;
import com.barbershop.crud.security.jwt.JwtProvider;
import com.barbershop.crud.security.jwt.JwtTokenFilter;
import com.barbershop.crud.security.repository.PerfilRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    PerfilRepository perfilRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    public Producto create(Integer usuario,
            String producto,
            String descripcion,
            String precio,
            String categoria,
            String rutaImg) {
        try {

            Producto miProducto = new Producto();
            miProducto.setDescripcion(descripcion);
            miProducto.setNombre(producto);
            miProducto.setPrecio(precio);
            miProducto.setCategoria(categoria);
            miProducto.setFoto(rutaImg);

            
            try {
                Optional<Perfil> usuarioOptional = perfilRepository.findById(usuario);
                if (usuarioOptional.isPresent()) {
                    miProducto.setPerfil(usuarioOptional.get());
                }
            } catch (Exception e) {
                System.out.println("error al buscar usuario");
            }

            productoRepository.save(miProducto);
            return miProducto;
        } catch (Exception e) {
            System.out.println("error al buscar usuario");
            return null;
        }

    }

    
     

       
    
    
    
    public List<Producto> list() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getOne(int id) {
        return productoRepository.findById(id);
    }

    public Optional<Producto> getByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }
    
    public List<Producto> getByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> getByPerfilId(int perfil_id) {
        return productoRepository.findByPerfilId(perfil_id);
    }

    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    public void delete(int id) {
        productoRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return productoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return productoRepository.existsByNombre(nombre);
    }
}
