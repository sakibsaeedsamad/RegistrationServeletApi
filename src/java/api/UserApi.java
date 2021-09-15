/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import dao.GenderDao;
import dao.RoleDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Gender;
import model.Role;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Sakib
 */
@WebServlet(name = "UserApi", urlPatterns = {"/UserApi"})
public class UserApi extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserApi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserApi at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);

        String requestCode = request.getParameter("requestCode");
        System.out.println("requestCode = " + requestCode);

        if ("1".equals(requestCode)) {
            String name = request.getParameter("name");
            System.out.println("name = " + name);
            String mobile = request.getParameter("mobile");
            System.out.println("mobile = " + mobile);
            String email = request.getParameter("email");
            System.out.println("email = " + email);
            String dob = request.getParameter("dob");
            System.out.println("dob = " + dob);
            String gender = request.getParameter("gender");
            System.out.println("gender = " + gender);
            String address = request.getParameter("address");
            System.out.println("address = " + address);
            String role = request.getParameter("role");
            System.out.println("role = " + role);

            User user = new User();

            user.setName(name);
            user.setMobile(mobile);
            user.setEmail(email);
            user.setDob(dob);
            user.setGender(gender);
            user.setAddress(address);
            user.setRole(role);

            try {
                JSONObject json = new JSONObject();
                UserDao dao = new UserDao();
                User outModel = dao.insertUserData(user);

                json.put("name", outModel.getName());
                json.put("mobile", outModel.getMobile());
                json.put("email", outModel.getEmail());
                json.put("dob", outModel.getDob());
                json.put("address", outModel.getAddress());
                json.put("gender", outModel.getGender());
                json.put("role", outModel.getRole());
                json.put("age", outModel.getAge());

                json.put("errorCode", "N");
                json.put("errorMessage", "Data Inserted");

                System.out.println("User Insert Json " + json);
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(json.toString());
                response.getWriter().flush();
            } catch (Exception ex) {
                Logger.getLogger(UserApi.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ("2".equals(requestCode)) {
            
            try {

                JSONArray jsonArray = new JSONArray();

                RoleDao roleDao = new RoleDao();
                for (Role role : roleDao.getRoleTypes(requestCode)) {

                    JSONObject json = new JSONObject();
                    json.put("code", role.getCode());
                    json.put("desc", role.getDesc());
                    jsonArray.put(json);

                }

                System.out.println("Role List: " + jsonArray);
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(jsonArray.toString());
                response.getWriter().flush();
            } catch (Exception ex) {
                Logger.getLogger(UserApi.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else if ("3".equals(requestCode)) {
            
            try {

                JSONArray jsonArray = new JSONArray();

                GenderDao genderDao = new GenderDao();
                for (Gender gender : genderDao.getGender(requestCode)) {

                    JSONObject json = new JSONObject();
                    json.put("genCode", gender.getGenCode());
                    json.put("genDesc", gender.getGenDesc());
                    jsonArray.put(json);

                }

                System.out.println("Gender List: " + jsonArray);
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(jsonArray.toString());
                response.getWriter().flush();
            } catch (Exception ex) {
                Logger.getLogger(UserApi.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        else if ("4".equals(requestCode)) {
            
            try {

                JSONArray jsonArray = new JSONArray();

                UserDao userDao = new UserDao();
                for (User user : userDao.getAllUser(requestCode)) {

                    JSONObject json = new JSONObject();
                    json.put("name", user.getName());
                    json.put("mobile", user.getMobile());
                    json.put("email", user.getEmail());
                    json.put("age", user.getAge());
                    json.put("address", user.getAddress());
                    json.put("gender", user.getGender());
                    json.put("role", user.getRole());
                    json.put("dob", user.getDob());
                    json.put("roledesc",user.getRoleDesc());
                    
                    jsonArray.put(json);

                }

                System.out.println("User List: " + jsonArray);
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(jsonArray.toString());
                response.getWriter().flush();
            } catch (Exception ex) {
                Logger.getLogger(UserApi.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
