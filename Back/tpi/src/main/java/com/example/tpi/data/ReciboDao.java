/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.tpi.data;

import com.example.tpi.data.exception.DaoException;
import com.example.tpi.dto.ReciboDto;
import com.example.tpi.dto.SueldoxArea;
import com.example.tpi.models.Recibo;
import java.util.List;

/**
 *
 * @author ROCCO
 */
public interface ReciboDao {
    void createRecibo(Recibo r) throws DaoException;
    List<Recibo>obtenerXLegajo(int legajo) throws DaoException;
    List<SueldoxArea> obtenerxMesAnio(int anio,int mes) throws DaoException;
}
