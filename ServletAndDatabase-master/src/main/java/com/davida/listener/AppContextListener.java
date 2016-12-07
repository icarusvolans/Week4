package com.davida.listener;

import com.davida.database.DBConnection;
import com.davida.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by davida on 12/2/16.
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        String url = ctx.getInitParameter("DBURL");
        String u = ctx.getInitParameter("DBUSER");
        String p = ctx.getInitParameter("DBPWD");

        //create database connection from init parameters and set it to context
        try {
            DBConnection dbManager = new DBConnection("nothing for now");
            ctx.setAttribute("DBManager", dbManager);
        } catch (Exception e) {
            System.out.println("In contextInitialized: " + e.toString());
        }
        getAllUsers(servletContextEvent);
        System.out.println("Database connection initialized for Application.");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        DBConnection dbManager = (DBConnection) ctx.getAttribute("DBManager");
        try {
            dbManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Database connection closed for Application.");

    }

    public static void getAllUsers(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        DBConnection dbManager = (DBConnection) ctx.getAttribute("DBManager");
        Connection conn = dbManager.getConn();

        try {
            Statement st = null;
            ResultSet rs = null;
            String sqlAll = "SELECT * from GUEST_INFO where CHECKOUT IS NULL";
            ArrayList<User> users = new ArrayList<>();

            st = conn.createStatement();
            rs = st.executeQuery(sqlAll);

            User u = new User();
            while (rs.next()) {
                u.setId(rs.getInt("ID"));
                u.setName(rs.getString("NAME"));
                u.setNoteIn(rs.getString("NOTE"));
                u.setCheckedIn(rs.getString("CHECKIN"));
                u.setNoteOut(rs.getString("NOTE_OUT"));
                u.setCheckedOut(rs.getString("CHECKOUT"));

                users.add(u);
                u = new User();
            }

                ctx.setAttribute("Users", users);
            } catch(SQLException e){
                e.printStackTrace();
            }
        }


    }

