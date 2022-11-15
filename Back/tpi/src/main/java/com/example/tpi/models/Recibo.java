/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

/**
 *
 * @author ROCCO
 */
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table (name="recibo")
public class Recibo {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nro_recibo")
    private int idRecibo;
    private int anio;
     private int mes;
      @Column(name="monto_antig")
    private double antiguedad;
    private double jubilacion;
      @Column(name="obra_social")
    private double obraSocial;
        @Column(name="fondo_complej")
    private double fondoComplejidad;
        @ManyToOne(targetEntity=Empleado.class)
        @JoinColumn(name="legajo")
        @JsonBackReference
        private Empleado empleado;

    public Recibo(int idRecibo, int anio, int mes, double jubilacion, double obraSocial, double fondoComplejidad, Empleado empleado) {
        this.idRecibo = idRecibo;
        this.anio = anio;
        this.mes = mes;
        this.jubilacion = jubilacion;
        this.obraSocial = obraSocial;
        this.fondoComplejidad = fondoComplejidad;
        this.empleado = empleado;
    }
      public Recibo(int idRecibo, int mes, int anio, double antiguedad, double jubilacion, double obraSocial, double fondoComplejidad, Empleado empleado) {
        this.idRecibo = idRecibo;
        this.mes = mes;
        this.anio = anio;
        this.antiguedad = antiguedad;
        this.jubilacion = jubilacion;
        this.obraSocial = obraSocial;
        this.fondoComplejidad = fondoComplejidad;
        this.empleado = empleado;
    }
    
        
        public Recibo(){
            
        }
     @Formula("(select empleado.sueldo_bruto+monto_antig-(obra_social+fondo_complej+jubilacion) from empleado where empleado.legajo=legajo)")
    private float sueldoNeto;
}
