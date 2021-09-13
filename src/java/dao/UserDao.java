/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import model.User;
import util.DBConnectionHandler;
 import java.util.Date;  

/**
 *
 * @author Sakib
 */
public class UserDao {
    
      public User insertUserData(User user) throws ParseException {
        User outModel = new User();
        Connection oConn = null;
        
                        
               
                
   
          
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate bDate = LocalDate.parse(user.getDob(), formatter);
        LocalDate now = LocalDate.now();
        long userAge = ChronoUnit.YEARS.between(bDate, now);
        System.out.println(userAge);
          

        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            oConn = DBConnectionHandler.getConVentionalConnection();
//
//            String sql = "INSERT INTO registrationservletdb.user (name,mobile,email,dob,gender,address,role) VALUES (?,?,?,?,?,?,?)";
//
//            PreparedStatement ps = oConn.prepareStatement(sql);
//
//            ps.setString(1, user.getName());
//            ps.setString(2, user.getMobile());
//            ps.setString(3, user.getEmail());
//            ps.setString(4, user.getDob());
//            ps.setString(5, user.getGender());
//            ps.setString(6, user.getAddress());
//            ps.setString(7, user.getRole());

            //ps.executeUpdate();
            //System.out.println("sql = " + sql);

            outModel.setName(user.getName());
            outModel.setMobile(user.getMobile());
            outModel.setEmail(user.getEmail());
            outModel.setDob(user.getDob());
            outModel.setGender(user.getGender());
            outModel.setAddress(user.getAddress());
            outModel.setRole(user.getRole());
            outModel.setAge(Long.toString(userAge));
            
//            outModel.setErrorCode("N");
//            outModel.setErrorMessage("User Inserted Successfully");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (oConn != null) {
                DBConnectionHandler.releaseConnection(oConn);
            }
        }
        return outModel;

    }
    
}
