/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.tpi.data;

import com.example.tpi.data.exception.DaoException;
import com.example.tpi.dto.EmpleadoDto;
import com.example.tpi.models.Empleado;
import java.util.List;

/**
 *
 * @author ROCCO
 */
public interface EmpleadoDao {
    List<Empleado> getAll()throws DaoException;
    void create(Empleado e) throws DaoException;
}
