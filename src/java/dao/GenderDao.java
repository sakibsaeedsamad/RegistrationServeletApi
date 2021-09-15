/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Gender;
import model.Role;
import model.User;
import util.DBConnectionHandler;

/**
 *
 * @author Sakib
 */
public class GenderDao {
      public List<Gender> getGender(String reqCode) {
        List<Gender> list = new ArrayList<>();
        Connection oConn = null;

        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            oConn = DBConnectionHandler.getConVentionalConnection();
            String sql = "SELECT * FROM registrationservletdb.gender ";
            PreparedStatement ps = oConn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("sql = " + sql);
            
             while (rs.next()) {

            Gender model = new Gender();
            
            model.setGenCode(rs.getString("gencode"));
            model.setGenDesc(rs.getString("gendesc"));
            list.add(model);
            
             }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (oConn != null) {
                DBConnectionHandler.releaseConnection(oConn);
            }
        }
        return list;

    }

    
}
