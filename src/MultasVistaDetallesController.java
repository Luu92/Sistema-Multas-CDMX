/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DTOModel.Multa;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Luu_PC
 */
public class MultasVistaDetallesController implements Initializable {
    
    public Pane  ap;  
    @FXML
    private Label label;
    @FXML
    private TableView<Multa> TablaDetalles;
    @FXML
    private TableColumn<Multa, String> ColFolio;
    @FXML
    private TableColumn<Multa, String> ColMotivo;
    @FXML
    private TableColumn<Multa, Double> ColCosto;
    @FXML
    private Button btnPagar;
    @FXML
    private Button btnAgendar;
    @FXML
    private TextArea txtFolio;
    @FXML
    private TextArea txtMotivo;
    @FXML
    private TextArea txtCosto;
    @FXML
    private Button btnAgregar;

    private final ObservableList<Multa> listaMultas = FXCollections.<Multa>observableArrayList();

    @FXML
    public void AccionAgregar(ActionEvent evento) {
        if (!txtFolio.getText().isEmpty() && !txtFolio.getText().isEmpty() && !txtCosto.getText().isEmpty()) {
            /*Multa multa = new Multa(txtFolio.getText(),
                                txtMotivo.getText(), 
                                Double.valueOf(txtCosto.getText()));
            listaMultas.add(multa);
            txtFolio.setText("");
            txtMotivo.setText("");
            txtCosto.setText("");*/
        } else {
            JOptionPane.showMessageDialog(null, "Deben estar Todos los campos", "Error!!!", 0);
        }
    }

    @FXML
    public void AccionPagar(ActionEvent evento) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormadePago.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            FXMLFormadePagoController controlador = (FXMLFormadePagoController) loader.getController();
            /*Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Gestión Vehicular");
            stage.setResizable(false);
            stage.getIcons().add(new Image(SistemaMultasPSP0.class.getResourceAsStream("/img/SistemaMultas-Logo.png")));
            stage.show();*/
            if (ap != null) {
                ap.getChildren().clear();
                ap.getChildren().add(root);
            }
            
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //ColFolio.setCellValueFactory( new PropertyValueFactory<>("folio"));
        //ColMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        //ColCosto.setCellValueFactory( new PropertyValueFactory<>("costo"));
        //Multa multa_1 = new Multa("A-1125","Estacionarse en lugares Prohibidos",158.54);
        //Multa multa_2 = new Multa("A-0024","Circular con carga en carriles centrales",5884.00);
        //Multa multa_3 = new Multa("A-1554","Estacionarse en lugares Prohibidos",8456.05);
        //listaMultas.addAll(multa_1,multa_2,multa_3);
        TablaDetalles.setItems(listaMultas);

    }

    @FXML
    public void regresaVentana() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormadePago.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            FXMLFormadePagoController controlador = (FXMLFormadePagoController) loader.getController();
            if (ap != null) {
                ap.getChildren().clear();
                ap.getChildren().add(root);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

}
