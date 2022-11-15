/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpi.data;

import com.example.tpi.data.exception.DaoException;
import com.example.tpi.dto.EmpleadoDto;
import com.example.tpi.dto.ReciboDto;
import com.example.tpi.dto.SueldoxArea;
import com.example.tpi.models.Empleado;
import com.example.tpi.models.Recibo;
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
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author ROCCO
 */
@Repository
@Setter
public class ReciboDaoImp implements ReciboDao {
      @PersistenceContext
    private EntityManager em;

 @Autowired
    private DataSource dataSource;
 
  @Override
    public List<Recibo> obtenerXLegajo (int legajo) throws DaoException {
        return (List<Recibo>) em.createQuery("SELECT e FROM Recibo e WHERE e.empleado.legajo=" + legajo, Recibo.class).getResultList();
        
    }
       @Transactional
    public void createRecibo(Recibo r) {
        Empleado emp = em.find(Empleado.class, r.getEmpleado().getLegajo());
        emp.addRecibo(r);

        em.merge(r);
    }
      @Override
    public List<SueldoxArea> obtenerxMesAnio(int a, int m) throws DaoException {
        
        List<SueldoxArea> lst = new ArrayList<>();
        String consulta = "SELECT * FROM sueldoxArea WHERE anio="+ a +" AND mes="+m;
        
        try ( Connection cnn = dataSource.getConnection();  Statement st = cnn.createStatement();  ResultSet rs = st.executeQuery(consulta);) {
            while (rs.next()) {
                int anio = rs.getInt("anio");
                int mes = rs.getInt("mes");
                String area = rs.getString("area");
                double neto = rs.getDouble("Neto");

                lst.add(new SueldoxArea(anio, mes, area, neto));

            }
        } catch (SQLException e) {
            throw new DaoException("Error de datos", e);
        }

        return lst;
    }
    
}
