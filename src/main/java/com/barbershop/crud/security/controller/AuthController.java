package com.barbershop.crud.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.barbershop.crud.dto.Mensaje;
import com.barbershop.crud.security.dto.JwtDto;
import com.barbershop.crud.security.dto.LoginUsuario;
import com.barbershop.crud.security.dto.NuevoUsuario;
import com.barbershop.crud.security.dto.PerfilDto;
import com.barbershop.crud.security.entity.Rol;
import com.barbershop.crud.security.entity.Usuario;

import com.barbershop.crud.security.enums.RolNombre;
import com.barbershop.crud.security.jwt.JwtProvider;
import com.barbershop.crud.security.service.PerfilService;

import com.barbershop.crud.security.service.RolService;
import com.barbershop.crud.security.service.UsuarioService;
import com.barbershop.crud.service.ProductoService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.json.JSONObject;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ProductoService productoService;

    @Autowired
    PerfilService perfilService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, PerfilDto perfilDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        }
        Usuario usuario
                = new Usuario(nuevoUsuario.getNombre(),
                         nuevoUsuario.getNombreUsuario(),
                         passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<PerfilDto> getById(@PathVariable("id") int id) {
        if (!usuarioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("usuario inexistente"), HttpStatus.OK);
        }
        if (!perfilService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Perfil inexistente"), HttpStatus.OK);
        }
        Usuario miPerfil = usuarioService.getById(id).get();
        PerfilDto perfil=new PerfilDto(
                miPerfil.getPerfilUsuario().getId(),
                miPerfil.getPerfilUsuario().getNombre()
                ,miPerfil.getPerfilUsuario().getFoto()
                ,miPerfil.getPerfilUsuario().getEmail()
                ,miPerfil.getPerfilUsuario().getTelefono()
                ,miPerfil.getPerfilUsuario().getDescripcion()
                ,miPerfil.getPerfilUsuario().getInstagram()
                ,miPerfil.getPerfilUsuario().getFacebook()
                ,miPerfil.getPerfilUsuario().getWhatsapp()
        );
        
        return new ResponseEntity(perfil, HttpStatus.OK);
    }

    @GetMapping("/perfil/{nombreUsuario}")
    public ResponseEntity<Usuario> getByNombreUsuario(@PathVariable("nombreUsuario") String nombreUsuario) {
        if (!usuarioService.existsByNombreUsuario(nombreUsuario)) {
            return new ResponseEntity(new Mensaje("usuario inexistente"), HttpStatus.NOT_FOUND);
        }
        Usuario miPerfil = usuarioService.getByNombreUsuario(nombreUsuario).get();
        JSONObject profile = new JSONObject();
        profile.put("nombre", miPerfil.getNombre());
        System.out.println(profile);
        String perfil = profile.toString();

        return new ResponseEntity(perfil, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Usuario miPerfil = usuarioService.getByNombreUsuario(loginUsuario.getNombreUsuario()).get();
        JwtDto jwtDto = new JwtDto(jwt,
                 userDetails.getUsername(),
                 miPerfil.getId(),
                 userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
