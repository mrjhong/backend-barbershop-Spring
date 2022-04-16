package com.barbershop.crud.security.service;

import com.barbershop.crud.repository.ProductoRepository;
import com.barbershop.crud.security.entity.Perfil;
import com.barbershop.crud.security.entity.Usuario;
import com.barbershop.crud.security.repository.PerfilRepository;
import com.barbershop.crud.security.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PerfilService {

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    UsuarioRepository usuarioRepository;
            
    public Perfil crear(Integer usuario,
            String email,
            String descripcion,
            String telefono,
            String rutaImg,
            String facebook,
            String instagram,
            Integer ApWhatsapp
           )
    {
            Usuario miperfil = usuarioService.getById(usuario).get();
            Perfil perfil = new Perfil();
       
       try {      
            perfil.setId(usuario);
            perfil.setNombre(miperfil.getNombre());
            perfil.setNombreUsuario(miperfil.getNombreUsuario());
            perfil.setEmail(email);
            perfil.setDescripcion(descripcion);       
            perfil.setTelefono(telefono);
            perfil.setFoto(rutaImg);
            perfil.setFacebook(facebook);
            perfil.setInstagram(instagram);
            
            if (ApWhatsapp==1) {
               perfil.setWhatsapp("https://api.whatsapp.com/send?phone="+
                       telefono+"&text=hola%20he%20%20visto%20tu%20catalogo%20"
                               + "de%20productos%20en%20freeCommerce%20y%20"
                               + "estoy%20interesado%20e%20adquirir%20uno"
                               + "%20de%20ellos");
           } else {
                perfil.setWhatsapp("0");
           }

            
            try {
               // Optional<Usuario> usuarioOptional = usuarioRepository.findById(dto.getUsuario());
                //if (usuarioOptional.isPresent()) {
                  //  perfil.setUsuarioPrivate(usuarioOptional.get());}
                 miperfil.setPerfilUsuario(perfil);
                 usuarioRepository.save(miperfil);
            
            } catch (Exception e) {
                System.out.println("error al buscar usuario");
            }
                        
            
           perfilRepository.save(perfil);

      
            return perfil;

        } catch (Exception e) {
            System.out.println("error al buscar usuario");
            return null;
        }

    }

    public Optional<Perfil> getByNombreUsuario(String nombreUsuario) {
        return perfilRepository.findByNombreUsuario(nombreUsuario);
    }

    public Optional<Perfil> getById(Integer id) {
        return perfilRepository.findById(id);
    }



    public boolean existsByEmail(String email) {
        return perfilRepository.existsByEmail(email);
    }

    public boolean existsById(int id) {
        return perfilRepository.existsById(id);
    }

    public void save(Perfil perfil) {
        perfilRepository.save(perfil);
    }

}
