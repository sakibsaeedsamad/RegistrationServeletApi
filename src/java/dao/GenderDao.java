/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Gender;
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

            Gender model = new Gender();
            Gender model1 = new Gender();
            

            model.setGenCode("1");
            model.setGenDesc("Female");

            model1.setGenCode("2");
            model1.setGenDesc("Male");

            
            list.add(model);
            list.add(model1);
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
