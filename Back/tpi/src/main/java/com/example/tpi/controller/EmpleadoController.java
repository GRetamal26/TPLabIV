/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpi.controller;

import com.example.tpi.data.EmpleadoDao;
import com.example.tpi.data.exception.DaoException;
import com.example.tpi.dto.EmpleadoDto;
import com.example.tpi.models.Empleado;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ROCCO
 */
@RestController
@Setter
@Getter
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoDao dao;
    
    @GetMapping("")
    public ResponseEntity<?> get(){
      
        try{
           
            return ResponseEntity.ok(dao.getAll());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
       
    }
    
      @PostMapping("/nuevo")
    public ResponseEntity<?> registrarEmpleado(@RequestBody EmpleadoDto empleadoDto) throws DaoException {
        Empleado empleado = new Empleado(
                empleadoDto.getNombre(),
                empleadoDto.getApellido(),
                empleadoDto.getFechaNacimiento(),
                empleadoDto.getFechaIngreso(),
                empleadoDto.getArea(),
                empleadoDto.getSueldoBruto());

        dao.create(empleado);
        return ResponseEntity.ok(empleado);

    }

}

