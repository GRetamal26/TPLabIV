/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

/**
 *
 * @author ROCCO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int legajo;
    private String nombre;
    private String apellido;
    @Column(name="fecha_nac")
    private Date fechaNacimiento;
    @Column(name="fecha_ingreso")
    private Date fechaIngreso;
    private String area;
    @Column(name="sueldo_bruto")
    private double sueldoBruto;
    
    @OneToMany(targetEntity=Recibo.class,mappedBy="empleado")
    @JsonManagedReference
    private List<Recibo>recibos;
    

    public Empleado(int legajo, String nombre, String apellido, Date fechaNacimiento, Date fechaIngreso, String area, double sueldoBruto) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.area = area;
        this.sueldoBruto = sueldoBruto;
    }

    public Empleado(int legajo, double sueldoBruto) {
        this.legajo = legajo;
        this.sueldoBruto = sueldoBruto;
    }

    public Empleado(int legajo, String nombre, String apellido, String area, double sueldoBruto) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.area = area;
        this.sueldoBruto = sueldoBruto;
    }
    
      public Empleado(String nombre, String apellido, Date fechaNacimiento, Date fechaIngreso, String area, double sueldoBruto) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.area = area;
        this.sueldoBruto = sueldoBruto;
    }
    public void addRecibo(Recibo r){
        r.setEmpleado(this);
        recibos.add(r);
    }
     @Formula("year(now())-year(fecha_ingreso)")
    private int antiguedad;
    

}
