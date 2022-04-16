package com.barbershop.crud.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barbershop.crud.dto.Mensaje;
import com.barbershop.crud.dto.ProductoDto;
import com.barbershop.crud.entity.Producto;
import com.barbershop.crud.repository.ProductoRepository;
import com.barbershop.crud.security.dto.PerfilUsuarioDto;
import com.barbershop.crud.security.entity.Perfil;
import com.barbershop.crud.security.entity.Usuario;
import com.barbershop.crud.security.jwt.JwtProvider;
import com.barbershop.crud.security.jwt.JwtTokenFilter;
import com.barbershop.crud.security.repository.PerfilRepository;
import com.barbershop.crud.security.repository.UsuarioRepository;
import com.barbershop.crud.security.service.PerfilService;
import com.barbershop.crud.security.service.UsuarioService;
import com.barbershop.crud.service.ProductoService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/servicio")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProductoService productoService;
    
    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    PerfilRepository perfilRepository;

      @Autowired
      PerfilService perfilService;
    
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    JwtTokenFilter jwtTokenFilter;
  


    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> list() {
        List<Producto> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id) {
        if (!productoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @GetMapping("/misproductos/{id}")
    public ResponseEntity<List<Producto>> PerfilId(@PathVariable("id") int id) {
        return new ResponseEntity(productoService.getByPerfilId(id), HttpStatus.OK);
    }
    
    @GetMapping("/category/{categoria}")
    public ResponseEntity<List<Producto>> getByCategoria(@PathVariable("categoria") String categoria) {
        return new ResponseEntity(productoService.getByCategoria(categoria), HttpStatus.OK);
    }
    
    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre) {
        if (!productoService.existsByNombre(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Producto producto = productoService.getByNombre(nombre).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('USER')") para darle permiso de crear a un tipo de usuario especifico

    @PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file,
                @RequestParam("usuario") Integer usuarioID,
                @RequestParam("producto") String producto,
                @RequestParam("descripcion") String descripcion,
                @RequestParam("precio") String precio,
                @RequestParam("categoria") String categoria,

                HttpServletRequest request,
                RedirectAttributes attributes)
			throws IOException {

            
                String Bearer = jwtProvider.getNombreUsuarioFromToken(jwtTokenFilter.getToken(request));
                Usuario miPerfil = usuarioService.getById(usuarioID).get();
                String seguridad = miPerfil.getNombreUsuario();
                
                if (Bearer.equals(seguridad)) {
		if (file == null || file.isEmpty()) {
			attributes.addFlashAttribute("message", "Por favor seleccione un archivo");
			return "redirect:status";
		}
                

		StringBuilder builder = new StringBuilder();
		
		builder.append(File.separator);
		builder.append("opt");
		builder.append(File.separator);
                builder.append("lampp");
                builder.append(File.separator);
                builder.append("htdocs");
                builder.append(File.separator);
                builder.append("productos");
                builder.append(File.separator);
                builder.append(usuarioID);
                
		builder.append(file.getOriginalFilename());
               
                
                String nameImg=usuarioID+file.getOriginalFilename();

		byte[] fileBytes = file.getBytes();
		Path path = Paths.get(builder.toString());
		Files.write(path, fileBytes);
		
		attributes.addFlashAttribute("message", "Archivo cargado correctamente ["+builder.toString()+"]");
                productoService.create(usuarioID, producto, descripcion, precio,categoria,nameImg);
		return "producto guardado";
                }else {
                return "ERROR";
   
                 }
                };
    
    
    
    
    
    
    
    
    
    //@PreAuthorize("hasRole('ADMIN')") para darle permiso de crear a un tipo de usuario especifico
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProductoDto productoDto,HttpServletRequest request) {
        
        String Bearer = jwtProvider.getNombreUsuarioFromToken(jwtTokenFilter.getToken(request));
        Producto miPerfil = productoService.getOne(id).get();
        String seguridad = miPerfil.getPerfil().getNombreUsuario();
        
        if (Bearer.equals(seguridad)){
        
        if (!productoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        if (productoService.existsByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(productoDto.getNombre())) {
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
      

        Producto producto = productoService.getOne(id).get();
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        productoService.save(producto);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
        }
        
        else{
            
        return new ResponseEntity(new Mensaje("error de autenticacion"), HttpStatus.OK); }
    }

    //@PreAuthorize("hasRole('ADMIN')") para darle permiso de crear a un tipo de usuario especifico
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id,HttpServletRequest request) {
        
        String Bearer = jwtProvider.getNombreUsuarioFromToken(jwtTokenFilter.getToken(request));
        Producto miPerfil = productoService.getOne(id).get();
        String seguridad = miPerfil.getPerfil().getNombreUsuario();
        
        if (Bearer.equals(seguridad))
        {
        if (!productoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
         productoRepository.delete(miPerfil);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
        }else{
        return new ResponseEntity(new Mensaje("error de autenticacion"), HttpStatus.OK);
        }

    }
    

}
