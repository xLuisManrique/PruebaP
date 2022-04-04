/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    public String driver = "com.mysql.jdbc.Driver";
    
    public String database = "prueba";
    
    public String hostname = "localhost";
    
    public String port = "3306";

    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database ;

    public String username = "root";

  
    public String password = "";

    public Conexion() {
    }

    public Connection conectarMySQL() {
        Connection conn = null;

        try {
            Class.forName(driver);
            
            conn = DriverManager.getConnection(url, username, password);
           
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
