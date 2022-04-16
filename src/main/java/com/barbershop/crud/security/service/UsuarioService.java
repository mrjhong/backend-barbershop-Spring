package com.barbershop.crud.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbershop.crud.security.entity.Usuario;
import com.barbershop.crud.security.repository.UsuarioRepository;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public Optional<Usuario> getById(Integer id){
     return usuarioRepository.findById(id);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }


   public boolean existsById(int id){
        return usuarioRepository.existsById(id);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
