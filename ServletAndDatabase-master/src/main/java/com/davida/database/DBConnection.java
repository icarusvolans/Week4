package com.davida.database;

import java.sql.*;

public class DBConnection {

    Connection conn;                                                //our connnection to the db - presist for life of program

    // we dont want this garbage collected until we are done
    public DBConnection(String dbName) throws Exception {    // note more general exception

        Class.forName("org.hsqldb.jdbcDriver");
       // conn = DriverManager.getConnection("jdbc:hsqldb:hsql:" + dbName, "SA", "");
        conn = DriverManager.getConnection("jdbc:hsqldb:file:/Applications/hsqldb-2.3.4/hsqldb/lib/GuestLogin;ifexists=false", "SA", "");

    }

    public Connection getConn() {

        return this.conn;
    }

    public void closeConnection() throws SQLException{
        conn.close();
    }

    public void shutdown() throws SQLException {

        Statement st = conn.createStatement();
        st.execute("SHUTDOWN");
        conn.close();
    }

    //use for SQL command SELECT
    public synchronized void query(String expression) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();
        rs = st.executeQuery(expression);

        st.close();
    }


}