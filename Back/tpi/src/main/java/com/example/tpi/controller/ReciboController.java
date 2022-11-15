/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpi.controller;

import com.example.tpi.data.EmpleadoDao;
import com.example.tpi.data.ReciboDao;
import com.example.tpi.data.exception.DaoException;
import com.example.tpi.dto.EmpleadoDto;
import com.example.tpi.dto.ReciboDto;
import com.example.tpi.models.Empleado;
import com.example.tpi.models.Recibo;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@Getter
@RequestMapping("/recibo")
public class ReciboController {
      @Autowired
    private ReciboDao dao;
    
       @GetMapping("/{legajo}")
    public ResponseEntity<?> obtenerXLegajo(@PathVariable int legajo) {
        if (legajo == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El legajo del empleado no puede ser 0");
        }
        try {
            return ResponseEntity.ok(dao.obtenerXLegajo(legajo));
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de datos!");
        }
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrarRecibo(@RequestBody ReciboDto reciboDto) throws DaoException {
        Empleado e = new Empleado();
        e.setLegajo(reciboDto.getLegajo());
        Recibo recibo = new Recibo(0,
                reciboDto.getMes(),
                reciboDto.getAnio(),
                reciboDto.getAntiguedad(),
                reciboDto.getJubilacion(),
                reciboDto.getObraSocial(),
                reciboDto.getFondoComplejidad(),
                e);
        dao.createRecibo(recibo);
        return ResponseEntity.ok(recibo);

    }
    
    @GetMapping("/{a}/{m}")
    public ResponseEntity<?> obtenerxMesAnio (@PathVariable int a, @PathVariable int m) {
        try {
            return ResponseEntity.ok(dao.obtenerxMesAnio(a,m));
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
      
}
