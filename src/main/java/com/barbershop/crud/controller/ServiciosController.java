package com.barbershop.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.barbershop.crud.entity.Servicios;


@RestController
@RequestMapping("/Servicios")
public class ServiciosController {
    @GetMapping("")
    public String list(){
        return "Servicios/list";
    }
    @GetMapping("/Create")
    public String create(){
    return "Servicios/create";
    } 
    
    @GetMapping("/save")
    public String save(Servicios servicios){
    return "Servicios/create";
    } 
}
