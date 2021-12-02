/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DTOModel.Multa;
import Data.MultaJDBC;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author DevSolutions
 */
public class MultasVistaDetallesController implements Initializable {
    
    public Pane  ap;  
    @FXML
    private TableView<Multa> TablaDetalles;
    @FXML
    private TableColumn<Multa, String> ColMotivo;
    @FXML
    private TableColumn<Multa, Double> ColCosto;
    @FXML
    private TableColumn<Multa, String> ColEstatus;
    @FXML
    private Button btnPagar;
    @FXML
    private Button btnAgendar;
    
    Multa multa;
    
    private String placa;
    FXMLDocumentController instancia;

    
    MultaJDBC listamultasJDBC = new MultaJDBC();
    private ObservableList<Multa> listaMultas = null;
   
    public void placaStage1(String txtBuscar){
        this.placa = txtBuscar;
        MultaJDBC listamultasJDBC = new MultaJDBC();
        listaMultas = FXCollections.observableArrayList();
        for (Multa listaMulta : listamultasJDBC.mostrarDatos()) {
            if(placa.equals(listaMulta.getPlaca())){
                listaMultas.add(listaMulta);
            }
            /*else{
                System.out.println("placa: " + listaMulta.getPlaca());
            }*/
        }  
        
        ColMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        ColCosto.setCellValueFactory( new PropertyValueFactory<>("precio"));
        ColEstatus.setCellValueFactory( new PropertyValueFactory<>("estado"));
        TablaDetalles.setItems(listaMultas);
    }

    @FXML
    public void irFormadePago() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormadePago.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            FXMLFormadePagoController controlador = (FXMLFormadePagoController) loader.getController();
            controlador.multa = multa;
            if (ap != null) {
                ap.getChildren().clear();
                ap.getChildren().add(root);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    @FXML
    public void agendarCita(){
        try {
            
                  
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCitas.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            FXMLCitasController controlador = (FXMLCitasController) loader.getController();
            controlador.placaStage1(placa);
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
        TablaDetalles.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.multa = newValue;});
        
    }
    
    
}
