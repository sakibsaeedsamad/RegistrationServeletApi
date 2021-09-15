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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import model.User;
import util.DBConnectionHandler;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sakib
 */
public class UserDao {

    public User insertUserData(User user) throws ParseException {
        User outModel = new User();
        Connection oConn = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate bDate = LocalDate.parse(user.getDob(), formatter);
        LocalDate now = LocalDate.now();
        //long userAge = ChronoUnit.YEARS.between(bDate, now

        Period userAge = Period.between(bDate, now);

        System.out.println(userAge.getYears() + "years" + userAge.getMonths() + "months" + userAge.getDays() + "days");
        System.out.println(userAge);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            oConn = DBConnectionHandler.getConVentionalConnection();

            String sql = "INSERT INTO registrationservletdb.user (name,mobile,email,dob,gender,address,role) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = oConn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getMobile());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getDob());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getRole());

            ps.executeUpdate();
            System.out.println("sql = " + sql);
            outModel.setName(user.getName());
            outModel.setMobile(user.getMobile());
            outModel.setEmail(user.getEmail());
            outModel.setDob(user.getDob());
            outModel.setGender(user.getGender());
            outModel.setAddress(user.getAddress());
            outModel.setRole(user.getRole());

            outModel.setAge(userAge.getYears() + "years" + userAge.getMonths() + "months" + userAge.getDays() + "days");

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

    public List<User> getAllUser(String reqCode) {
        List<User> list = new ArrayList<>();
        Connection oConn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            oConn = DBConnectionHandler.getConVentionalConnection();
            String sql = " SELECT registrationservletdb.user.name, registrationservletdb.user.mobile,registrationservletdb.user.email,registrationservletdb.user.dob,registrationservletdb.user.gender,registrationservletdb.user.address,registrationservletdb.user.role, registrationservletdb.role.desc  \n" +
"FROM user   \n" +
"INNER JOIN role  \n" +
"ON registrationservletdb.user.role = registrationservletdb.role.code";
            PreparedStatement ps = oConn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("sql = " + sql);

            while (rs.next()) {

                User model = new User();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                LocalDate bDate = LocalDate.parse(rs.getString("dob"), formatter);
                LocalDate now = LocalDate.now();
                Period userAge = Period.between(bDate, now);

                model.setName(rs.getString("name"));
                model.setMobile(rs.getString("mobile"));
                model.setEmail(rs.getString("email"));
                model.setDob(rs.getString("dob"));
                model.setGender(rs.getString("gender"));
                model.setAddress(rs.getString("address"));
                model.setRole(rs.getString("role"));
                model.setRoleDesc(rs.getString("desc"));
                model.setAge(userAge.getYears() + "years" + userAge.getMonths() + "months" + userAge.getDays() + "days");

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

    public static void main(String[] args) throws ParseException {

//        Value Check
//        UserDao userDao = new UserDao();
//        User model = new User();
//        model.setName("Saeed");
//        model.setMobile("01521318802");
//        model.setEmail("saeed@gmail.com");
//        model.setDob("12/02/1997");
//        model.setGender("Male");
//        model.setAddress("Dhaka");
//        model.setRole("1");
//        
//        //System.out.println("model = " + model);
//        User insertInfo = userDao.insertUserData(model);
//        System.out.println("Name = " + insertInfo.getName());
//        System.out.println("Mobile = " + insertInfo.getMobile());
//        System.out.println("Email = " + insertInfo.getEmail());
//        System.out.println("Dob = " + insertInfo.getDob());
//        System.out.println("Gender = " + insertInfo.getGender());
//        System.out.println("Role = " + insertInfo.getRole());
//        System.out.println("ErrorMessage = " + insertInfo.getErrorMessage());
//        System.out.println("ErrorCode = " + insertInfo.getErrorCode());
    }

}
