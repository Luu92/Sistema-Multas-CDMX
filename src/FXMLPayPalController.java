
import DTOModel.Multa;
import DTOModel.Ticket;
import DTOModel.ValidaDatos;
import Data.MultaJDBC;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author DevSolutions
 */
public class FXMLPayPalController implements Initializable {

    @FXML
    private Label lbtotal;
    @FXML
    private TextField tfcorreo;
    @FXML
    private PasswordField pfpass;
    @FXML
    private Button btnpagarcon;
    @FXML
    private AnchorPane ap;
    @FXML
    private ImageView imgregresa;
    @FXML
    private Hyperlink hlregistra ;
    
    public Multa multa;

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    public Multa getMulta() {
        return multa;
    }

    public Label getLbtotal() {
        return lbtotal;
    }

    public void setLbtotal(String precio) {
        lbtotal.setText(precio);
    }
    
    @FXML
    private void validaCuenta() {
        if (ValidaDatos.validarCorreo(tfcorreo.getText()) && ValidaDatos.validarPassword(pfpass.getText())) {
            JOptionPane.showMessageDialog(null, "Procesando pago", "Sistema de Pagos", JOptionPane.WARNING_MESSAGE);
            Ticket t = new Ticket();
            t.creaTicket();
            MultaJDBC multaPagada = new MultaJDBC();
            multaPagada.actualizarMulta(multa);
            regresaVentanaListaMultas();
        }
        if (tfcorreo.getText().isEmpty() || ValidaDatos.validarCorreo(tfcorreo.getText()) == false) {
            tfcorreo.setText(null);
            tfcorreo.setStyle("-fx-prompt-text-fill: red;");
            tfcorreo.setPromptText("*Campo incorrecto");
        }
        if (pfpass.getText().isEmpty() || ValidaDatos.validarPassword(pfpass.getText()) == false) {
            pfpass.setText(null);
            pfpass.setStyle("-fx-prompt-text-fill: red;");
            pfpass.setPromptText("*Campo incorreto");
        }
    }

    @FXML
    public void regresaVentana() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormadePago.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            FXMLFormadePagoController controlador = (FXMLFormadePagoController) loader.getController();

            ap.getChildren().clear();
            ap.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    @FXML
    public void registra(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.paypal.com/mx/webapps/mpp/account-selection"));
    }

    public void regresaVentanaListaMultas() {
        try {
            
           FXMLLoader loader = new FXMLLoader(getClass().getResource("MultasVistaDetalles.fxml"));
                Parent root = loader.load();
                MultasVistaDetallesController controlador = loader.getController();
                controlador.placaStage1(multa.getPlaca().toUpperCase());
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                stage.getIcons().add(new Image(FXMLDocumentController.class.getResourceAsStream("/img/SistemaMultas-Logo.png")));
                Stage nuevoStage = (Stage) btnpagarcon.getScene().getWindow();
                nuevoStage.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicializaDatos(null);
        
    }

}
