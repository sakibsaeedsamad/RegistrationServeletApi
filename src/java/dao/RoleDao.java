/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Role;
import util.DBConnectionHandler;

/**
 *
 * @author Sakib
 */
public class RoleDao {

      public List<Role> getRoleTypes(String reqCode) {
        List<Role> list = new ArrayList<>();
        Connection oConn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            oConn = DBConnectionHandler.getConVentionalConnection();
            String sql = "SELECT * FROM registrationservletdb.role ";
            PreparedStatement ps = oConn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("sql = " + sql);
            
             while (rs.next()) {

            Role model = new Role();
            
            model.setCode(rs.getString("code"));
            model.setDesc(rs.getString("desc"));
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
