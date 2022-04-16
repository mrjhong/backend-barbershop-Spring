package com.barbershop.crud.security.controller;

import com.barbershop.crud.dto.Mensaje;
import com.barbershop.crud.security.dto.PerfilUsuarioDto;
import com.barbershop.crud.security.entity.Perfil;
import com.barbershop.crud.security.entity.Usuario;
import com.barbershop.crud.security.jwt.JwtProvider;
import com.barbershop.crud.security.jwt.JwtTokenFilter;
import com.barbershop.crud.security.repository.UsuarioRepository;
import com.barbershop.crud.security.service.PerfilService;
import com.barbershop.crud.security.service.UsuarioService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/secperfil")
@CrossOrigin(origins = "*")
public class PerfilController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerfilService perfilService;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @PostMapping("/crearperfil")
    public ResponseEntity<Perfil> crear(@RequestBody PerfilUsuarioDto dto, HttpServletRequest request,BindingResult bindingResult) {
   
        String Bearer = jwtProvider.getNombreUsuarioFromToken(jwtTokenFilter.getToken(request));
        Usuario miPerfil = usuarioService.getById(dto.getUsuario()).get();
        String seguridad = miPerfil.getNombreUsuario();
        if (Bearer.equals(seguridad) && !perfilService.existsById(dto.getUsuario()) ) {
            System.out.println("hola mundo");
            // perfilService.crear(dto);
            return new  ResponseEntity(new Mensaje("perfil actualizado"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("error en la autenticacion"), HttpStatus.OK);
        }
        
    };

    
    @PostMapping("/upload")
	public ResponseEntity<?>uploadFile(@RequestParam("file") MultipartFile file,
                @RequestParam("usuario") Integer usuarioID,
                @RequestParam("email") String email,
                @RequestParam("descripcion") String descripcion,
                @RequestParam("telefono") String telefono,
                @RequestParam("facebook") String facebook,
                @RequestParam("instagram") String instagram,
                @RequestParam("ApWhatsapp") Integer ApWhatsapp,    
                HttpServletRequest request,
                RedirectAttributes attributes)
			throws IOException {

            
                String Bearer = jwtProvider.getNombreUsuarioFromToken(jwtTokenFilter.getToken(request));
                Usuario miPerfil = usuarioService.getById(usuarioID).get();
                String seguridad = miPerfil.getNombreUsuario();
                
                if (Bearer.equals(seguridad)) {
		StringBuilder builder = new StringBuilder();
		
		builder.append(File.separator);
		builder.append("opt");
		builder.append(File.separator);
                builder.append("lampp");
                builder.append(File.separator);
                builder.append("htdocs");
                builder.append(File.separator);
                builder.append("perfiles");
                builder.append(File.separator);
                builder.append(usuarioID);
                
		builder.append(file.getOriginalFilename());
               
                
                String nameImg=usuarioID+file.getOriginalFilename();

		byte[] fileBytes = file.getBytes();
		Path path = Paths.get(builder.toString());
		Files.write(path, fileBytes);
		
		attributes.addFlashAttribute("message", "Archivo cargado correctamente ["+builder.toString()+"]");
                perfilService.crear(usuarioID, 
                        email, 
                        descripcion, 
                        telefono, 
                        nameImg, 
                        facebook, 
                        instagram, 
                        ApWhatsapp);
                    return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.OK);
                }else {
                  return new ResponseEntity(new Mensaje("error"), HttpStatus.OK);

   
                 }
                };
    
        


    
}
