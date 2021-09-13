/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sakib
 */
public class DBConnectionHandler {


    Connection con = null;
    
    public static Connection getConVentionalConnection() {
        Connection con = null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con =DriverManager.getConnection("jdbc:mysql://localhost:3306/registrationservletdb", "root", "root");


   } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }
    
    public static void releaseConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    
}