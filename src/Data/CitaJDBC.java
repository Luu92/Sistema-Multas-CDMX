package Data;

import DTOModel.Cita;
import static Data.ConeccionDB.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DevSolutions
 */
public class CitaJDBC {

    private static final String SQL_SELECT = "SELECT * FROM cita";
    private static final String SQL_INSERT = "INSERT INTO cita(fecha, hora, verificentro, placa) VALUES (?,?,?,?)";

    public List<Cita> mostrarDatos() {
        Connection coneccion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cita cita = null;
        List<Cita> listaCitas = new ArrayList<>();
        try {
            coneccion = getConecction();
            stmt = coneccion.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int folio = rs.getInt("numcita");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");
                String verificentro = rs.getString("verificentro");
                String placa = rs.getString("placa");
                cita = new Cita(fecha, hora, verificentro, placa);
                cita.setId(folio);
                listaCitas.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                ConeccionDB.close(rs);
                ConeccionDB.close(stmt);
                ConeccionDB.close(coneccion);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return listaCitas;
    }

    public void insertarCita(Cita cita) throws SQLException {
        Connection coneccion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int nuevoRegistro = 0;
        try {
            coneccion = getConecction();
            stmt = coneccion.prepareStatement(SQL_INSERT);
            stmt.setString(1, cita.getFecha());
            stmt.setString(2, cita.getHora());
            stmt.setString(3, cita.getVerificentro());
            stmt.setString(4, cita.getPlaca());
            nuevoRegistro = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(coneccion);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

}
