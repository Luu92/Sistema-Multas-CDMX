/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DTOModel.Multa;
import static Data.ConeccionDB.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author DevSolutions
 */
public class MultaJDBC {
    
    private static final String SQL_SELECT = "SELECT folio,motivo,precio,placa,estado FROM multa";
    private static final String SQL_UPDATE = "UPDATE multa SET estado = ? WHERE  = ?";
    
    public List<Multa> mostrarDatos(){
        Connection coneccion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Multa multa = null;
        List<Multa> listaMultas = new ArrayList<>(); 
        try {
            coneccion = getConecction();
            stmt = coneccion.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int folio     = rs.getInt("folio");
                String motivo = rs.getString("motivo");
                float precio  = rs.getFloat("precio");
                String placa  = rs.getString("placa");
                String estado = rs.getString("estado");
                multa = new Multa(folio,motivo,precio,placa,estado);
                listaMultas.add(multa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                ConeccionDB.close(rs);
                ConeccionDB.close(stmt);
                ConeccionDB.close(coneccion);
            } catch (SQLException ex) {
               ex.printStackTrace(System.out);
            }
        }
        return listaMultas;
    }
    
    public void actualizarMulta(Multa multa){
        Connection coneccionUpdate = null;
        PreparedStatement stmt = null;
        int eliminados = 0;
        try {
            coneccionUpdate = getConecction();
            stmt = coneccionUpdate.prepareStatement(SQL_UPDATE);
            stmt.setString(1,multa.getEstado());
            stmt.setString(2,multa.getPlaca());
            eliminados = stmt.executeUpdate();
            
        } catch (Exception e) {
          e.printStackTrace(System.out);
        } finally{
            try {
                close(stmt);
                close(coneccionUpdate);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
    }
    
    
}
