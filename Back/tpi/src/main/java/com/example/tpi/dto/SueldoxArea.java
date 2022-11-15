/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpi.dto;

import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ROCCO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "sueldoxArea")
public class SueldoxArea {
     private int anio;
    private int mes;
    private String area;
    private double neto;
    
}
