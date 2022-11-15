/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpi.data;

import com.example.tpi.data.exception.DaoException;
import com.example.tpi.dto.EmpleadoDto;
import com.example.tpi.models.Empleado;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ROCCO
 */
@Repository
@Setter
public class EmpleadoDaoImp implements EmpleadoDao {
    @PersistenceContext
    private EntityManager em;

      @Autowired
    private DataSource dataSource;
      
      @Override
    public List<Empleado> getAll() throws DaoException {
        return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
    }
    
    @Override
     @Transactional
    public void create(Empleado e) {

        em.merge(e);
    }

}
