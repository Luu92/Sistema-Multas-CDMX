
import DTOModel.Cita;
import Data.CitaJDBC;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.styledxmlparser.jsoup.nodes.Element;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import java.sql.SQLException;//Tratamiento de Errros de BD SQL
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;


public class FXMLCitasController implements Initializable {
    //clases de conexion y sus objetos

    private Label lblHora;
    @FXML
    private Label lblDelegacionYvereficentro;
    @FXML
    private DatePicker calendarioCita;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnImprimir;
    @FXML
    private TextField txtaAgregarHora;
    @FXML
    private TextField txtAgregarMinutos;
    @FXML
    private TextField txtPuntos;
    private TableView<Cita> tabla_consultar;

    private TableColumn<Cita, String> columna_fecha;

    private TableColumn<Cita, String> columna_hora;

    private TableColumn<Cita, String> columna_numCita;

    private TableColumn<Cita, String> columna_lugar;

    private TableColumn<Cita, String> colPlaca;

    @FXML
    private ComboBox<String> cbTlahuac;

    @FXML
    private ComboBox<String> cbCoyoacan;

    @FXML
    private ComboBox<String> cbAlbaroObregon;

    @FXML
    private ComboBox<String> cbAzcapotzalco;

    @FXML
    private ComboBox<String> cbBenitoJuarez;

    @FXML
    private ComboBox<String> cbCuajimalpa;

    @FXML
    private ComboBox<String> cbCuauhtemoc;

    @FXML
    private ComboBox<String> cbGustavoMadero;

    @FXML
    private ComboBox<String> cbIztacalco;

    @FXML
    private ComboBox<String> cbIztapalapa;

    @FXML
    private ComboBox<String> cbMiguelHidalgo;

    @FXML
    private ComboBox<String> cbTlalplan;

    @FXML
    private ComboBox<String> cbVenustianoCarranza;

    @FXML
    private ComboBox<String> cbXochimilco;

    @FXML
    private ComboBox<String> cbAmPm;


    public String placa;
   
    private static String DESTINO = "C:\\Users\\DevLuu\\Desktop\\Cita Verificacion.pdf";
    @FXML
    private Label etiqueraFecha;
    @FXML
    private Label etiquetaHora;
    @FXML
    public void agendar() throws SQLException {
        LocalDate hoy = LocalDate.now();
        LocalDate fechaHoy = calendarioCita.getValue();
        String formatoDeFecha = fechaHoy.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));//formato para poner el dia, mes y año 

        if (fechaHoy == null || fechaHoy.isBefore(hoy)) { // condicion para no escoger las fechas anteriores al actual 
            JOptionPane.showMessageDialog(null, "La fecha " + formatoDeFecha + " ha caducado\n\n          seleccione otra");
            //lblCitaFecha.setText("fecha invalida"); //mostrar en el label fecha invalida
        } else {
            etiqueraFecha.setText(formatoDeFecha);
            etiquetaHora.setText(txtaAgregarHora.getText() + ":" + txtAgregarMinutos.getText() + " HRS");
            CitaJDBC nuevaCita = new CitaJDBC();
            Cita CitaNueva = new Cita(formatoDeFecha, etiquetaHora.getText(), lblDelegacionYvereficentro.getText(), placa);
            nuevaCita.insertarCita(CitaNueva);
        }
    }

    public String placaStage1(String placa) {
        this.placa = placa;
        return this.placa;
    }

    @FXML
    public void imprimirCita() throws IOException {
        
        try {
            PdfWriter crearCitaPDF = new PdfWriter(DESTINO);
            PdfDocument archivoPDF = new PdfDocument(crearCitaPDF);
            PdfFont estiloFuente = PdfFontFactory.createFont(FontConstants.COURIER);
            Paragraph titulo = new Paragraph("Cita Verificación Control Vehicular de la CDMX");
            
           
            
            titulo.setBold();
            titulo.setFontSize(15f);
            titulo.setFont(estiloFuente);
            
            
           
            
            Paragraph cuerpo = new Paragraph();
            cuerpo.add("\nDatos CVV: " + lblDelegacionYvereficentro.getText()
                      + "\nPlacas: " + placa.toString()
                      + "\nFecha Cita: " + etiqueraFecha.getText()
                      + "\nHora Cita :" + etiquetaHora.getText());
            cuerpo.setFont(estiloFuente);
            try (Document diseñoPDF = new Document(archivoPDF)) {
                diseñoPDF.add(titulo);
                diseñoPDF.add(cuerpo);
               
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
        
    }

    @FXML
    public void comboxTlahuac() {
        String tlahuac = cbTlahuac.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(tlahuac);
    }

    @FXML
    public void comboxCoyoacan() {
        String coyoacan = cbCoyoacan.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(coyoacan);

    }

    @FXML
    public void comboxAlbaroObregon(ActionEvent event) {
        String albaroObregon = cbAlbaroObregon.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(albaroObregon);

    }

    @FXML
    public void comboxAzcapotzalco(ActionEvent event) {
        String azcapotzalco = cbAzcapotzalco.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(azcapotzalco);

    }

    @FXML
    public void comboxBenitoJuarez(ActionEvent event) {
        String benitoJuarez = cbBenitoJuarez.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(benitoJuarez);
    }

    @FXML
    public void comboxCuajimalpa(ActionEvent event) {
        String cuajimalpa = cbCuajimalpa.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(cuajimalpa);

    }

    @FXML
    public void comboxCuauhtemoc(ActionEvent event) {
        String cuauhtemoc = cbCuauhtemoc.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(cuauhtemoc);
    }

    @FXML
    public void comboxGustavoMadero(ActionEvent event) {
        String gustavoMadero = cbGustavoMadero.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(gustavoMadero);
    }

    @FXML
    public void comboxIztacalco(ActionEvent event) {
        String iztacalco = cbIztacalco.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(iztacalco);
    }

    @FXML
    public void comboxIztapalapa(ActionEvent event) {
        String iztapalapa = cbIztapalapa.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(iztapalapa);
    }

    @FXML
    public void comboxMiguelHidalgo(ActionEvent event) {
        String miguelHidalgo = cbMiguelHidalgo.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(miguelHidalgo);
    }

    @FXML
    public void comboxTlalplan(ActionEvent event) {
        String tlalplan = cbTlalplan.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(tlalplan);

    }

    @FXML
    public void comboxVenustianoCarranza(ActionEvent event) {
        String venustianoCarranza = cbVenustianoCarranza.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(venustianoCarranza);

    }

    @FXML
    public void comboxXochimilco(ActionEvent event) {
        String xochimilco = cbXochimilco.getSelectionModel().getSelectedItem().toString();
        lblDelegacionYvereficentro.setText(xochimilco);
    }

    @FXML
    void comboxAmPm(ActionEvent event) {
        double hora;
        hora = Double.parseDouble(txtaAgregarHora.getText());
        if (hora >= 20 || hora < 9) {
            JOptionPane.showMessageDialog(null, "Horario no disponible");
        } else {
            String AmPm = cbAmPm.getSelectionModel().getSelectedItem().toString();
            lblHora.setText(txtaAgregarHora.getText() + txtPuntos.getText() + txtAgregarMinutos.getText() + AmPm);
        }
    }

    @FXML
    public void salir() {

        int confirmaSalida = JOptionPane.showConfirmDialog(null, "¿Quieres salir?", "Mensaje importante", JOptionPane.YES_NO_OPTION);
        if (confirmaSalida == 0) {
            System.exit(0);//cierra ventana     
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //combobox delegacion tlahuac 
        cbTlahuac.getItems().add("DELEGACION TLAHUAC: VERIFICENTRO--> SUMINISTROS Y MAQUINARIA KEBEC, S.A. DE C.V.");
        cbTlahuac.getItems().add("DELEGACION TLAHUAC: VERIFICENTRO--> SOLUCIONES EMPRESARIALES DRAKO, S DE R.L. DE C.V.");

        //combobox delegacion coyoacan
        cbCoyoacan.getItems().add("DELEGACION COYOACÁN: VERIFICENTRO--> ARTE AUTOMOTRÍZ, S. DE R.L. DE C.V.");
        cbCoyoacan.getItems().add("DELEGACION COYOACÁN: VERIFICENTRO--> CONTROL ATMOSFÉRICO DE MÉXICO, S.A. DE C.V.");
        cbCoyoacan.getItems().add("DELEGACION COYOACÁN: VERIFICENTRO--> COMPAÑÍA EMPRESARIAL VEDOZA, S.A. DE C.V.");
        cbCoyoacan.getItems().add("DELEGACION COYOACÁN: VERIFICENTRO--> GRUPO ORNUTO S.A. DE C.V.");
        cbCoyoacan.getItems().add("DELEGACION COYOACÁN: VERIFICENTRO--> NOVA AMBIENTAL, S.A. DE C.V");
        cbCoyoacan.getItems().add("DELEGACION COYOACÁN: VERIFICENTRO--> CONTROL ECOLÓGICO COYOACÁN, S.A. DE C.V.");

        //combobox delegacion Álvaro Obregón
        cbAlbaroObregon.getItems().add("DELEGACION ÁLVARO OBREGÓN: VERIFICENTRO--> CONTROL ATMOSFÉRICO DE MÉXICO, S.A. DE C.V.");
        cbAlbaroObregon.getItems().add("DELEGACION ÁLVARO OBREGÓN: VERIFICENTRO--> CIUDAD MAXIPLUS, S. A. DE C. V.");
        cbAlbaroObregon.getItems().add("DELEGACION ÁLVARO OBREGÓN: VERIFICENTRO--> IMPULSORA ECOLÓGICA SANTA FE S.A. DE C.V..");
        cbAlbaroObregon.getItems().add("DELEGACION ÁLVARO OBREGÓN: VERIFICENTRO--> ASEROEMPRESA, S.A DE C.V.");

        //combobox delegacion Azcapotzalco
        cbAzcapotzalco.getItems().add("DELEGACION AZCAPOTZALCO: VERIFICENTRO--> VERIFICACIÓN INTEGRAL DE EMISIONES, S.A. DE C.V.");
        cbAzcapotzalco.getItems().add("DELEGACION AZCAPOTZALCO: VERIFICENTRO--> POWER CARS MUNDO, S. A. DE C. V.");
        cbAzcapotzalco.getItems().add("DELEGACION AZCAPOTZALCO: VERIFICENTRO--> EXCELENCIA REFACCIONARIA AVE FENIX, S. A. DE C. V..");
        cbAzcapotzalco.getItems().add("DELEGACION AZCAPOTZALCO: VERIFICENTRO--> CONTROL ATMOSFÉRICO DE MÉXICO, S.A. DE C.V.");
        cbAzcapotzalco.getItems().add("DELEGACION AZCAPOTZALCO: VERIFICENTRO--> ESTACIONAMIENTO RICE, S. A.");
        cbAzcapotzalco.getItems().add("DELEGACION AZCAPOTZALCO: VERIFICENTRO--> VERIFICENTRO EL ROSARIO, S.A. DE C.V.");

        //combobox delegacion Benito Juárez
        cbBenitoJuarez.getItems().add("DELEGACION BENITO JUAREZ: VERIFICENTRO--> EXCELENCIA REFACCIONARIA AVE FENIX, S. A. DE C. V.");
        cbBenitoJuarez.getItems().add("DELEGACION BENITO JUAREZ: VERIFICENTRO--> CONTROL ATMOSFÉRICO DE MÉXICO, S.A. DE C.V.");
        cbBenitoJuarez.getItems().add("DELEGACION BENITO JUAREZ: VERIFICENTRO--> VICENTE SERGIO PEREA FLORES");
        cbBenitoJuarez.getItems().add("DELEGACION BENITO JUAREZ: VERIFICENTRO--> VERIFICENTRO SIGLO XXI, S.A. DE C.V.");

        //combobox delegacion Cuajimalpa de Morelos
        cbCuajimalpa.getItems().add("DELEGACION CUAJIMALPA DE MORELOS: VERIFICENTRO--> CONTROL ATMOSFÉRICO DE MÉXICO, S.A. DE C.V.");
        cbCuajimalpa.getItems().add("DELEGACION CUAJIMALPA DE MORELOS: VERIFICENTRO--> GRUPO CONTADERO, S. A. DE C. V.");

        //combobox delegacion Cuauhtémoc
        cbCuauhtemoc.getItems().add("DELEGACION CUAUHTÉMOC: VERIFICENTRO--> ECO AMBIENTAL CUAUHTÉMOC, S.A DE C.V.");
        cbCuauhtemoc.getItems().add("DELEGACION CUAUHTÉMOC: VERIFICENTRO--> VERIFICENTRO, S.A. DE C.V.");
        cbCuauhtemoc.getItems().add("DELEGACION CUAUHTÉMOC: VERIFICENTRO--> VERIFICENTROS PROVIDA, S. A. DE C. V.");
        cbCuauhtemoc.getItems().add("DELEGACION CUAUHTÉMOC: VERIFICENTRO--> GESTIÓN DEL AIRE, S.A. DE C.V.");
        cbCuauhtemoc.getItems().add("DELEGACION CUAUHTÉMOC: VERIFICENTRO--> DIAGNOSTICO DE EMISIONES, S.A. DE C.V.");
        cbCuauhtemoc.getItems().add("DELEGACION CUAUHTÉMOC: VERIFICENTRO--> DESARROLLO Y ASESORÍA APLICADA NOVO, S.A. DE C.V.");

        //combobox delegacioN Gustavo A. Madero
        cbGustavoMadero.getItems().add("DELEGACION GUSTAVO A. MADERO: VERIFICENTRO--> MAC 1, S.A DE C.V.");
        cbGustavoMadero.getItems().add("DELEGACION GUSTAVO A. MADERO: VERIFICENTRO--> RONI, S.A DE C.V.");
        cbGustavoMadero.getItems().add("DELEGACION GUSTAVO A. MADERO: VERIFICENTRO--> IMPULSORA ECOLÓGICA BARRAGÁN, S.A. DE C.V.");
        cbGustavoMadero.getItems().add("DELEGACION GUSTAVO A. MADERO: VERIFICENTRO--> INGENIERÍA Y VERIFICACIÓN PANTITLÁN, S.A. DE C.V.");
        cbGustavoMadero.getItems().add("DELEGACION GUSTAVO A. MADERO: VERIFICENTRO--> JOSÉ RAFAEL OCAÑA UMBRAL");
        cbGustavoMadero.getItems().add("DELEGACION GUSTAVO A. MADERO: VERIFICENTRO--> DINÁMICA EN EQUIPO INDUSTRIAL, S.A DE C.V.");
        cbGustavoMadero.getItems().add("DELEGACION GUSTAVO A. MADERO: VERIFICENTRO--> ECOLOGÍA AMBIENTAL COPILCO, S.A. DE C.V.");
        cbGustavoMadero.getItems().add("DELEGACION GUSTAVO A. MADERO: VERIFICENTRO--> CERVITEC, SERVICIO TÉCNICO ECOLÓGICO, S.A. DE C.V.");

        //combobox delegacioN Iztacalco
        cbIztacalco.getItems().add("DELEGACION IZTACALCO: VERIFICENTRO--> INGENIERÍA Y VERIFICACIÓN PANTITLÁN, S.A. DE C.V.");
        cbIztacalco.getItems().add("DELEGACION IZTACALCO: VERIFICENTRO--> POWER CARS MUNDO, S. A. DE C. V.");
        cbIztacalco.getItems().add("DELEGACION IZTACALCO: VERIFICENTRO--> CIUDAD MAXIPLUS, S. A. DE C. V.");
        cbIztacalco.getItems().add("DELEGACION IZTACALCO: VERIFICENTRO--> SUMINISTROS Y MAQUINARIA KEBEC, S.A. DE C.V.");
        cbIztacalco.getItems().add("DELEGACION IZTACALCO: VERIFICENTRO--> DESARROLLO Y ASESORÍA APLICADA NOVO, S.A. DE C.V.");

        //combobox delegacioN Iztapalapa
        cbIztapalapa.getItems().add("DELEGACION IZTAPALAPA: VERIFICENTRO--> POWER CARS MUNDO, S. A. DE C. V.");
        cbIztapalapa.getItems().add("DELEGACION IZTAPALAPA: VERIFICENTRO--> EXCELENCIA REFACCIONARIA AVE FENIX, S. A. DE C. V.");
        cbIztapalapa.getItems().add("DELEGACION IZTAPALAPA: VERIFICENTRO--> DISTRIBUIDORA MIVEP S.A. DE C.V.");
        cbIztapalapa.getItems().add("DELEGACION IZTAPALAPA: VERIFICENTRO--> CIUDAD MAXIPLUS, S. A. DE C. V..");
        cbIztapalapa.getItems().add("DELEGACION IZTAPALAPA: VERIFICENTRO--> MAC 2, S.A DE C.V.");
        cbIztapalapa.getItems().add("DELEGACION IZTAPALAPA: VERIFICENTRO--> MAC 3, S.A DE C.V.");
        cbIztapalapa.getItems().add("DELEGACION IZTAPALAPA: VERIFICENTRO--> MAC 4, S.A DE C.V.");
        cbIztapalapa.getItems().add("DELEGACION IZTAPALAPA: VERIFICENTRO--> COMPAÑÍA EMPRESARIAL VEDOZA, S.A. DE C.V.");
        cbIztapalapa.getItems().add("DELEGACION IZTAPALAPA: VERIFICENTRO--> MEDINDUSTRIAS, S.A. DE C.V.");
        cbIztapalapa.getItems().add("DELEGACION IZTAPALAPA: VERIFICENTRO--> SOLUCIONES EMPRESARIALES DRAKO, S DE R.L. DE C.V.");
        cbIztapalapa.getItems().add("DELEGACION IZTAPALAPA: VERIFICENTRO--> SISTEMA AUTOMOTRIZ DE EXCELENCIA, S.A. DE C.V.");

        //combobox delegacioN Miguel Hidalgo
        cbMiguelHidalgo.getItems().add("DELEGACION MIGUEL HIDALGO: VERIFICENTRO--> CONTROL ATMOSFÉRICO DE MÉXICO, S.A. DE C.V. ");
        cbMiguelHidalgo.getItems().add("DELEGACION MIGUEL HIDALGO: VERIFICENTRO--> EXCELENCIA REFACCIONARIA AVE FENIX, S. A. DE C. V.");
        cbMiguelHidalgo.getItems().add("DELEGACION MIGUEL HIDALGO: VERIFICENTRO--> JET VAN CAR RENTAL S.A. DE C.V.");
        cbMiguelHidalgo.getItems().add("DELEGACION MIGUEL HIDALGO: VERIFICENTRO--> SERVICIO DE MEDICIÓN ORGANIZADA DE GASES, S. A. DE C. V");
        cbMiguelHidalgo.getItems().add("DELEGACION MIGUEL HIDALGO: VERIFICENTRO--> PURO AIRE, S.A. DE C.V.");
        cbMiguelHidalgo.getItems().add("DELEGACION MIGUEL HIDALGO: VERIFICENTRO--> VERIFICENTRO SAN JOAQUIN, S. A. DE C.V.");

        //combobox delegacioN Tlalpan
        cbTlalplan.getItems().add("DELEGACION TLALPAN: VERIFICENTRO--> MARÍA CAROLINA AGOFF");
        cbTlalplan.getItems().add("DELEGACION TLALPAN: VERIFICENTRO--> MECANICA AMBIENTAL, S.A. DE C.V.");

        //combobox delegacion Venustiano Carranza
        cbVenustianoCarranza.getItems().add("DELEGACION VENUSTIANO CARRANZA: VERIFICENTRO--> VERIFICACIONES CENTENARIO, S.A. DE C.V.");
        cbVenustianoCarranza.getItems().add("DELEGACION VENUSTIANO CARRANZA: VERIFICENTRO--> ALEJANDRO MARTÍNEZ BRIBIESCA");

        //combobox delegacioN Xochimilco
        cbXochimilco.getItems().add("DELEGACION XOCHIMILCO: VERIFICENTRO--> VERIFICACIONES CENTENARIO, S.A. DE C.V.");
        cbXochimilco.getItems().add("DELEGACION XOCHIMILCO: VERIFICENTRO--> CIUDAD MAXIPLUS, S. A. DE C. V.");
        cbXochimilco.getItems().add("DELEGACION XOCHIMILCO: VERIFICENTRO--> CONSULTORÍA PRO AMBIENTAL, S.A. DE C.V.");
        cbXochimilco.getItems().add("DELEGACION XOCHIMILCO: VERIFICENTRO--> TECNO AMBIENTAL NATIVITAS, S.A. DE C.V.");

        //combobox delegacioN Xochimilco
        cbAmPm.getItems().add("AM");
        cbAmPm.getItems().add("PM");

    }

}
