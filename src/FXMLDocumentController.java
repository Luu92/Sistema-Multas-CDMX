
import com.sun.javafx.scene.control.skin.Utils;
import java.sql.Connection;//conexion a BD
import java.sql.DriverManager;//driver de conexion
import java.sql.ResultSet;//resultado final de datos
import java.sql.ResultSetMetaData;//resultado de metadatos
import java.sql.SQLException;//Tratamiento de Errros de BD SQL
import java.sql.Statement;//Generador de sentencias SQL
import java.text.SimpleDateFormat;//Formatear datos
import java.util.Date;//fecha de sistema
import javax.swing.ImageIcon;//Tratamiento de imagenes
import javax.swing.JOptionPane;//ventanas emergentes
import javax.swing.table.DefaultTableModel;//tabla de datos

//LIBRERIAS PARA CONECTARSE A LA BASE DE DATOS
import java.sql.Connection;//conexion a BD
import java.sql.DriverManager;//driver de conexion
import java.sql.PreparedStatement;
import java.sql.ResultSet;//resultado final de datos
import java.sql.ResultSetMetaData;//resultado de metadatos
import java.sql.SQLException;//Tratamiento de Errros de BD SQL
import java.sql.Statement;//Generador de sentencias SQL
import java.text.SimpleDateFormat;//Formatear datos
import java.time.DayOfWeek;
import java.time.format.FormatStyle;
import java.util.Date;//fecha de sistema
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.JTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Mi Pc
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnSalir;

    @FXML
    private TextField txtBuscar;
    //clases de conexion y sus objetos
    public Connection cn;
    public Statement stmt;
    public ResultSet rs;

      //metodo para conectar a base de datos
    public void conectarBase() {
        try { //inicia try
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost/bdtransito", "root", "");
            stmt = cn.createStatement();//genera sentencias sobre objetos de base de datos
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de base de datos 1: \n" + ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de base de datos 2:" + e);
        }
    }//termina metodo conectar

    @FXML
    private void eventButton(ActionEvent event) {

        try {
            conectarBase();

            rs = stmt.executeQuery("select * from tplaca where placa ='" + txtBuscar.getText() + "'");

            JOptionPane.showMessageDialog(null, "Buscando placa...");
            if (rs.next() == true) {
                //imprime datos de libro
                JOptionPane.showMessageDialog(null, "TIENE MULTAS PENDIENTES");

            } else {
                JOptionPane.showMessageDialog(null, "no tienes multa");
                JOptionPane.showMessageDialog(null, "0 Sin adeudos");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de BD Busqueda" + ex);
        }

    }

    @FXML
    public void Salir() {

        int confirmaSalida = JOptionPane.showConfirmDialog(null, "¿Quieres salir?", "Mensaje importante", JOptionPane.YES_NO_OPTION);
        if (confirmaSalida == 0) {
            System.exit(0);//cierra ventana    
        }

    }

    @FXML
    public void Atencion() {

        String Vehicular = (JOptionPane.showInputDialog(null, "TRAMITES EN LINEA", "TRAMITES", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Licencias", "Tramites", "Multas"}, "Selecciona")).toString();

        JOptionPane.showConfirmDialog(null, "Elegiste: " + Vehicular, "TRAMITES", JOptionPane.PLAIN_MESSAGE);

        if (Vehicular == "Licencias") {
            JOptionPane.showMessageDialog(null, "INFORMACION\n\n " + "(Clave:01) Licencia A vehiculos particulares por 3 años (expedición, reposición y renovación)...	$900.00\n\n" + "(Clave:01) Licencia A vehiculos particulares por 3 años (expedición, reposición y renovación)...	$900.00\n\n"
                    + " (Clave:02) Licencia A 1 motociclistas por 3 años (expedición, reposición y renovación)...	$450.00\n\n"
                    + " (Clave:03) Licencia A automovilista y motociclista Permanente (Reposición)...	$900.00\n\n"
                    + " (Clave:04) Licencia A2 vehiculos particulares y motociclista (expedición, reposición y renovación)...	$900.00\n\n"
                    + " (Clave:05) Licencia Tarjetón B de taxis 2 años (Expedición, renovación y reposición)...	$1,117.00\n\n"
                    + " (Clave:07) Licencia Tarjetón B de taxis 3 años (Expedición, renovación y reposición)...	$1,681.00\n\n"
                    + " (Clave:09) Licencia Tarjetón C pasajeros, D carga, E emergencia por 2 años (Expedición, renovación y reposición)...	$1,619.00\n\n"
                    + " (Clave:11) Licencia Tarjetón C pasajeros, D carga, E emergencia por 3 años (Expedición, renovación y reposición)...	$2,431.00\n\n"
                    + " (Clave:13) Antecedentes Licencias A, B, C, D, E o permisos de conducir...	$205.00\n\n"
                    + " (Clave:14) Permiso de Conducir con Vigencia Única (expedición y reposición)...	$462.00");
        } else if (Vehicular == "Tramites") {

            String Multas1 = (JOptionPane.showInputDialog(null, "TRAMITES EN LINEA", "TRAMITES", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"(Clave:36) Autos particulares",
                "(Clave:37) Taxis",
                "(Clave:38) Vehículos de Pasajeros (PÚBLICO Y PARTICULAR)",
                "(Clave:39) Vehículos de Carga",
                "(Clave:40) Motocicletas",
                "(Clave:41) Bicicletas y Motocicletas adaptadas",
                "(Clave:42) Remolques",
                "(Clave:43) Permisos Transporte Mercantíl, Carga y Pasajeros",
                "(Clave:45) Servicios Diversos"}, "Selecciona")).toString();

            if (Multas1 == "(Clave:36) Autos particulares") {

                JOptionPane.showMessageDialog(null, " (2) Baja\n"
                        + "$ 465.00\n"
                        + " (4) Permiso de carga ocasional por siete días\n"
                        + "$ 110.00\n"
                        + " (5) Permiso para circular sin placas (30 días)\n"
                        + "$ 233.00\n"
                        + " (7) Por cambio de propietario, carrocería, motor, domicilio y corrección de datos\n"
                        + "$ 340.50\n"
                        + " (8) Cualquier otro permiso que no exceda 90 días\n"
                        + "$ 340.50\n"
                        + " (9) Cualquier otro servicio distinto a los señalados\n"
                        + "$ 340.50\n"
                        + " (10) Por reposición de calcomanía\n"
                        + "$ 233.00\n"
                        + " (15) Reposición o renovación de tarjeta de circulación\n"
                        + "$ 340.50");
            } else if (Multas1 == "(Clave:37) Taxis") {
                JOptionPane.showMessageDialog(null, "CONTROL VEHICULAR\n"
                        + " (2) Baja o suspensión de vehiculo\n"
                        + "$ 695.00\n"
                        + " (3) Reposición de placas cada una\n"
                        + "$ 1,144.50\n"
                        + " (4) Reposición de tarjeta de circulación\n"
                        + "$ 233.00\n"
                        + " (5) Permiso para circular sin placas (60 días)\n"
                        + "$ 927.00\n"
                        + " (6) Permisos para circular con aditamentos a la carrocería\n"
                        + "$ 339.00\n"
                        + " (7) Cambio o correccion de datos\n"
                        + "$ 181.00\n"
                        + " (8) Sustitución de vehículos y emisión de tarjeta de circulación\n"
                        + "$ 576.00\n"
                        + " (9) Autorización de modificación cromática oficial\n"
                        + "$ 1,435.00");

            } else if (Multas1 == "(Clave:38) Vehículos de Pasajeros (PÚBLICO Y PARTICULAR)") {
                JOptionPane.showMessageDialog(null, "CONTROL VEHICULAR\n"
                        + " (1) Alta Servicio Público de Transporte\n"
                        + "$ 1,586.00\n"
                        + " (2) Servicio privado de transporte\n"
                        + "$ 1,504.00\n"
                        + " (3) Baja o suspensión de vehiculo\n"
                        + "$ 590.00\n"
                        + " (4) Reposición de placa Servicio publico de transporte\n"
                        + "$ 1,126.00\n"
                        + " (5) Reposición de placa Servicio particular de transporte\n"
                        + "$ 934.00\n"
                        + " (6) Reposición de tarjeta de circulación\n"
                        + "$ 232.00\n"
                        + " (7) Permiso para circular sin placas (60 dias)\n"
                        + "$ 929.00\n"
                        + " (8) Para circular con aditamentos a la carrocería\n"
                        + "$ 343.00\n"
                        + " (9) Por cambio de carrocería, motor, domicilio y corrección de datos\n"
                        + "$ 179.50\n"
                        + " (10) Sustitución de vehículos de servicio publico de pasajeros o servicio de pasajeros\n"
                        + "$ 575.00\n"
                        + " (11) Por cualquier otro servicio distinto a los anteriores\n"
                        + "$ 343.00");

            } else if (Multas1 == "(Clave:39) Vehículos de Carga") {
                JOptionPane.showMessageDialog(null, "CONTROL VEHICULAR\n"
                        + " (2) Baja o suspensión de vehiculo\n"
                        + "$ 590.00\n"
                        + " (3) Reposicion de placas de Vehículos de servicio de transporte de carga cada una\n"
                        + "$ 1,126.00\n"
                        + " (4) Reposición de tarjeta de circulación\n"
                        + "$ 232.00\n"
                        + " (5) Permiso para circular sin placas (60 dias)\n"
                        + "$ 929.00\n"
                        + " (6) Para circular con aditamentos a la carrocería\n"
                        + "$ 343.00\n"
                        + " (7) Cambio o correccion de datos\n"
                        + "$ 179.50\n"
                        + " (8) Sustitución de vehículos de servicio publico de carga\n"
                        + "$ 575.00\n"
                        + " (9) Servicio de transporte de carga particular\n"
                        + "$ 1,217.00\n"
                        + " (10) Autorización especial para vehículos de más de 3.5 tons\n"
                        + "$ 2,043.00\n"
                        + " (11) Por cualquier otro servicio distinto a los anteriores\n"
                        + "$ 343.00");

            } else if (Multas1 == "(Clave:40) Motocicletas") {
                JOptionPane.showMessageDialog(null, "CONTROL VEHICULAR\n"
                        + " (2) Baja\n"
                        + "$ 382.00\n"
                        + " (4) Permiso para circular sin placa (30 días)\n"
                        + "$ 233.00\n"
                        + " (5) Cambio de propietario y correccion de datos incluye la expediciòn de nueva tarjeta de circulacion\n"
                        + "$ 223.00\n"
                        + " (6) Cualquier servicio distinto a los anteriores\n"
                        + "$ 181.00");

            } else if (Multas1 == "(Clave:41) Bicicletas y Motocicletas adaptadas") {
                JOptionPane.showMessageDialog(null, "CONTROL VEHICULAR\n"
                        + " (3) Baja\n"
                        + "$ 240.00\n"
                        + " (4) Reposición de placa por pérdida\n"
                        + "$ 1,226.00\n"
                        + " (5) Reposición de placa por deterioro o mutilación\n"
                        + "$ 463.00\n"
                        + " (6) Reposición de tarjeta de circulación\n"
                        + "$ 391.00\n"
                        + " (7) Permiso para circular sin placa\n"
                        + "$ 438.00\n"
                        + " (8) Cambio de propietario o domicilio de datos incluyendo la expedición de tarjeta de circulación\n"
                        + "$ 391.00\n"
                        + " (9) Cualquier servicio distinto a los anteriores\n"
                        + "$ 316.00");

            } else if (Multas1 == "(Clave:42) Remolques") {
                JOptionPane.showMessageDialog(null, "CONTROL VEHICULAR\n"
                        + " (1) Alta\n"
                        + "$ 1,352.00\n"
                        + " (2) Baja\n"
                        + "$ 382.00\n"
                        + " (4) Permiso para circular sin placa (60 días)\n"
                        + "$ 463.00\n"
                        + " (5) por reposicion de tarjeta de circulación o por cambio de propietario o domicilio, incluyendo la expedición de la tarjeta de circulación\n"
                        + "$ 223.00\n"
                        + " (6) Cualquier servicio distinto a los anteriores\n"
                        + "$ 371.00");

            } else if (Multas1 == "(Clave:43) Permisos Transporte Mercantíl, Carga y Pasajeros") {
                JOptionPane.showMessageDialog(null, "PARA OTORGAMIENTO O PRORROGA POR AÑO\n"
                        + " (1) De valores\n"
                        + "$ 2,043.00\n"
                        + " (2) De mensajería\n"
                        + "$ 2,043.00\n"
                        + " (3) Sustancias tóxicas o peligrosas\n"
                        + "$ 2,775.00\n"
                        + " (4) Especializado de pasajero y carga\n"
                        + "$ 2,357.00\n"
                        + " (5) Grúas\n"
                        + "$ 2,357.00\n"
                        + " (6) Escolar y de personal\n"
                        + "$ 2,043.00\n"
                        + " (7) Transporte de turistas en recorridos especificos\n"
                        + "$ 25,143.00\n"
                        + " (8) Establecimiento y equipamiento de auxiliar, por cada cajon\n"
                        + "$ 1,388.00\n"
                        + " (9) Vigencia anual de recorrido, por vehiculo\n"
                        + "$ 729.00\n"
                        + " (10) Reposición de permiso\n"
                        + "$ 2,736.00\n"
                        + " (11) Realización de estudios técnicos, para recorrido y bases\n"
                        + "$ 838.00");

            } else if (Multas1 == "(Clave:45) Servicios Diversos") {
                JOptionPane.showMessageDialog(null, "CONTROL VEHICULAR\n"
                        + " (1) Expedición de placas de demostración sin engomado\n"
                        + "$ 1,864.00\n"
                        + " (2) Arrastre hasta 3.5tons\n"
                        + "$ 788.00\n"
                        + " (3) Arrastre mayor a 3.5 tons\n"
                        + "$ 1,571.00\n"
                        + " (4) Almacenaje por día\n"
                        + "$ 82.00");

            }

        } else if (Vehicular == "Multas") {

            JOptionPane.showMessageDialog(null, "Multas Vehiculares\n"
                    + "\n"
                    + " (Clave: 47) .Retiro de candado inmovilizador y multas.	\n"
                    + "$ 0.00\n"
                    + "\n"
                    + " (Clave: 48) .Retiro de Candado Inmovilizador en lugares de personas con discapacidad y multas.	\n"
                    + "$ 0.00");

        }

     //   int confirmaSalida=JOptionPane.showConfirmDialog(null,"¿Quieres salir?","Mensaje importante",JOptionPane.YES_NO_OPTION);
        // if (confirmaSalida==0) {
        //   System.exit(0);//cierra ventana    
        // }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
