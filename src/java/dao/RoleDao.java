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
    
      public List<Role> getRoleTypes(String reqCode){
           List<Role> list = new ArrayList<>();
            Connection oConn = null;
        
        
        try {
            
                Role model = new Role();

                model.setCode("1");
                model.setDesc("Trainee Software Engineer");
//                model.setCode("2");
//                model.setDesc("Assistant Software Engineer");     
//                model.setCode("3");
//                model.setDesc("Jr Software Engineer");
//                model.setCode("4");
//                model.setDesc("Software Engineer");

                
                
                list.add(model);
            
        }  catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (oConn != null) {
                DBConnectionHandler.releaseConnection(oConn);
            }
        }
        return list;

          
      }
    
}
