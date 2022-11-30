package com.alexdev.gestionempleadosv3.util;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DBUtil {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    //insert username and password
    private static final String USER = "HR";
    private static final String PASS = "123";
    private static final String connStr = "jdbc:oracle:thin:HR/HR@localhost:1521/xe";
    private static Connection conn = null;


    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("No se ha encontrado el driver JDBC. Error: " + e);
            e.printStackTrace();
            throw e;
        }

        System.out.println("Driver JDBC encontrado. Intentando conectar con la base de datos...");

        try {
            conn = DriverManager.getConnection(connStr, USER, PASS);
        } catch (SQLException e) {
            System.out.println("No se ha podido conectar con la base de datos. Error: " + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
            throw e;
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;
//        CachedRowSetImpl crs = null;
//        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();


        CachedRowSet crs;
        try {
            dbConnect();
            System.out.println("Ejecutando la consulta SQL: " + queryStmt);

            stmt = conn.createStatement();

            resultSet = stmt.executeQuery(queryStmt);
            RowSetFactory aFactory = RowSetProvider.newFactory();
            crs = aFactory.createCachedRowSet();

//            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Ops! Algo ha ido mal en la ejecución de la consulta SQL. Error: " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Ops! Algo ha ido mal en la ejecución de la actualización SQL. Error: " + e);
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }
}