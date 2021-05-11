package org.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabrielhs
 */
public class ConexionDB implements Serializable{

    private static ConexionDB instance;
    private static Connection conn;

    private ConexionDB() {
        String urlDatabase = "jdbc:postgresql://localhost:5432/Crud3";
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDatabase,"postgres","1999");
            System.out.println("CONEXION EXITOSA");
        } catch (Exception e) {
            System.out.println("ERROR EN LA CONEXION:" + e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public static ConexionDB getInstance() {
        if (conn == null) {
            instance = new ConexionDB();
        }
        return instance;
    }

    public boolean execute(String sql) {
        boolean res = false;
        Statement st = null;
        try {
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }

}
