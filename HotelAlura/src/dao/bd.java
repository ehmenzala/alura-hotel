/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author shmen
 */
public class bd {

    public bd() {
    }
    
    public Connection getOracle(){
        Connection c = null;
        String usr = "ALURA";
        String pwd = "alura";
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        try {
            DriverManager.registerDriver(new OracleDriver());
            c = DriverManager.getConnection(url, usr, pwd);
        } catch (SQLException ex) {
            //Logger.getLogger(bd.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error de conexion: " + ex.getMessage());
        }        
        return c;
    }
    
}
