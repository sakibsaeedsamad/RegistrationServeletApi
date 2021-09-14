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

            Role model = new Role();
            Role model1 = new Role();
            Role model2 = new Role();
            Role model3 = new Role();

            model.setCode("1");
            model.setDesc("Trainee Software Engineer");

            model1.setCode("2");
            model1.setDesc("Assistant Software Engineer");

            model2.setCode("3");
            model2.setDesc("Jr Software Engineer");
            model3.setCode("4");
            model3.setDesc("Software Engineer");

            list.add(model);
            list.add(model1);
             list.add(model2);
            list.add(model3);

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
