/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.amu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author q13000412
 */
public class ConnexionUnique {
    final static private ConnexionUnique instance = new ConnexionUnique();
    final private Connection connection;
    final static private String CONNECT_URL = "jdbc:mysql://127.0.0.1/tpjdbc";

    private ConnexionUnique() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(CONNECT_URL, "jdbc", "");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        connection = conn;
    }

    public static ConnexionUnique getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
