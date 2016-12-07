package com.davida.servlets;

import com.davida.database.DBConnection;
import com.davida.model.Admin;
import com.davida.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by icarusvolans on 12/5/16.
 */
public class AdminServlet extends HttpServlet {
    public static HashMap<String, Admin> admins;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String uri = request.getRequestURI();
        System.out.println("Requested URI: " + uri);

        String jspName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println("JSP Name: " + jspName);

        System.out.println("in loginservlet doPost!!!!");

        String adminName = request.getParameter("name");
        System.out.println("Name: " + adminName);
        String adminPassword = request.getParameter("password");
        System.out.println("Password: " + adminPassword);

        DBConnection dbConnection = (DBConnection) getServletContext().getAttribute("DBManager");
        Connection con = dbConnection.getConn();
        ArrayList<User> users1 = (ArrayList<User>) getServletContext().getAttribute("Users");
        request.setAttribute("Users1", users1);

        Statement st = null;
        Statement st2 = null;

        ResultSet rs = null;
        try {
            String sql = "INSERT INTO admin_info(ADMIN_NAME, ADMIN_PASSWORD) VALUES ('" + adminName + "', '" + adminPassword + "')";
            String sqlPassword = "SELECT ADMIN_PASSWORD from ADMIN_INFO WHERE ADMIN_NAME= '" + adminName + "'";
            String sqlCheckout = "UPDATE guest_info SET CHECKOUT=CURRENT_TIMESTAMP, NOTE_OUT='Checked out by admin.' WHERE ID in()";
            st = con.createStatement();
            st2 = con.createStatement();

//            if(User.isFirstLogin())
//            if(st.executeQuery("SELECT CHECKOUT from GUEST_INFO WHERE NAME= '" + userName + "'") == null)
            rs = st.executeQuery(sqlPassword);

            if (!rs.next() || rs == null) {

                st2.executeUpdate(sql);
                jspName = "viewAllGuests";

            }else if (!rs.getString("ADMIN_PASSWORD").equals(adminPassword)) {

                jspName = "adminLogin";

            }else if(jspName.equals("logout")) {
                st2.executeUpdate(sqlCheckout);
                jspName = "viewAllGuests";

            }else
                jspName = "viewAllGuests";



        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("In catch: " + e.toString());
            throw new ServletException("DB Connection problem.");
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
            } catch (SQLException e) {
//                e.printStackTrace();
                System.out.println("In finally: " + e.toString());

            }
        }

        RequestDispatcher view = request.getRequestDispatcher("/admin/" + jspName + ".jsp");
        view.forward(request, response);


    }



    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println();
        doPost(request, response);
    }

}

