package com.davida.servlets;


import com.davida.database.DBConnection;
import com.davida.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

/**
 * Created by davida on 11/22/16.
 */
public class LoginServlet extends HttpServlet {

    public static HashMap<String, User> users;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        System.out.println("in loginservlet doPost!!!!");

        String userName = request.getParameter("name");
        System.out.println("Name: "+ userName);
        String userNote = request.getParameter("note");
        System.out.println("Comments: "+ userNote);

        DBConnection dbConnection = (DBConnection) getServletContext().getAttribute("DBManager");
        Connection con = dbConnection.getConn();
//        LoginServlet db = null;

        Statement st = null;
        Statement st2 = null;

        ResultSet rs = null;
        try {
            String sql = "INSERT INTO guest_info(NAME, CHECKIN, NOTE) VALUES ('" + userName + "', CURRENT_TIMESTAMP, '" + userNote + "')";
            String sqlOut = "UPDATE guest_info SET CHECKOUT=CURRENT_TIMESTAMP, NOTE_OUT='" + userNote + "' WHERE NAME='" + userName + "'";
            String selectSQL = "SELECT CHECKOUT from GUEST_INFO WHERE NAME= '" + userName + "'";
            st = con.createStatement();
            st2 = con.createStatement();

             rs = st.executeQuery(selectSQL);
                if (!rs.next() || rs == null) {
                    st2.executeUpdate(sql);
                }else
                    if (rs.getTimestamp("CHECKOUT") == null)
                        st2.executeUpdate(sqlOut);


        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("In catch: " + e.toString());
            throw new ServletException("DB Connection problem.");
        }finally {
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
            RequestDispatcher view = request.getRequestDispatcher("/guests/login.jsp");
            view.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println();
        doPost(request, response);
    }

}
