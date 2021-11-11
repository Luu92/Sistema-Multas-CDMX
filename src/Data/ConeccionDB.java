/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DevSolutions
 */
public class ConeccionDB {
    
    private static final String DATABASE = "SistemaMultas";
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/" + DATABASE + "?";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASS = "123456";
    
    public static Connection getConecction() throws SQLException{
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    
    public static void close(Statement smtm) throws SQLException{
        smtm.close();
    }
    
    public static void close(PreparedStatement smtm) throws SQLException{
        smtm.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    } 
    
}
