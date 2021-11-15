
package Data;

import DTOModel.Auto;
import static Data.ConeccionDB.getConecction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Devsolutions
 */
public class AutoJDBC {
    
    private static final String SQL_SELECT = "SELECT placa,marca,anio,id_propietario FROM auto";
    
    public List<Auto> mostrarDatos(){
        Connection coneccion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auto nuevoAuto = null;
        List<Auto> listAutos = new ArrayList<>(); 
        try {
            coneccion = getConecction();
            stmt = coneccion.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                String placa = rs.getString("placa");
                String marca = rs.getString("marca");
                int anio     = rs.getInt("anio");
                int propietario = rs.getInt("id_propietario");
                nuevoAuto = new Auto(placa, marca, anio, propietario);
                listAutos.add(nuevoAuto);
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
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
        return listAutos;
    }
            
    
    
    
}
