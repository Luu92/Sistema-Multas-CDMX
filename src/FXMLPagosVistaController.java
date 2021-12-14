/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import DTOModel.ValidaDatos;
import java.time.ZoneId;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import DTOModel.BinTarjetas;
import DTOModel.Multa;
import DTOModel.Ticket;
import Data.MultaJDBC;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class FXMLPagosVistaController implements Initializable {

    @FXML
    private Button btnpagar;
    @FXML
    private Label lbtitular;
    @FXML
    private Label lbtarjeta;
    @FXML
    private Label lbcvv;
    @FXML
    private TextField tftitular;
    @FXML
    private TextField tftarjeta;
    @FXML
    private ChoiceBox<String> tfmes;
    @FXML
    private TextField tfanio;
    @FXML
    private PasswordField pfcvv;
    @FXML
    private Label lbmonto;
    @FXML
    private Pane panetitulo;
    @FXML
    private Label lbtitulo;
    @FXML
    private Label tffolio;
    @FXML
    private AnchorPane ap;

    Multa multa;

    private final String[] mes = {"01-Ene", "02-Feb", "03-Mar", "04-Abr", "05-May", "06-Jun", "07-Jul", "08-Ago",
        "09-Sep", "10-Oct", "11-Nov", "12-Dic"};
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int month = localDate.getMonthValue();

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setLbmonto(Label lbmonto) {
        this.lbmonto = lbmonto;
    }

    public Label getLbmonto() {
        return lbmonto;
    }

    @FXML
    private void validarTarjeta() {

        if (ValidaDatos.validarNombre(tftitular.getText()) && ValidaDatos.validarTarjeta(tftarjeta.getText())
                && ValidaDatos.validarAnio(tfanio.getText()) && !tfmes.getSelectionModel().isEmpty()
                && ValidaDatos.validarCvv(pfcvv.getText()) && caducoTarjeta() == false && encuentraBanco() == true) {

            Ticket t = new Ticket();
            t.creaTicket();
            
        }
        if (tftitular.getText().isEmpty() || ValidaDatos.validarNombre(tftitular.getText()) == false) {
            tftitular.setText(null);
            tftitular.setStyle("-fx-prompt-text-fill: red;");
            tftitular.setPromptText("*Campo incorrecto");
        }
        if (tftarjeta.getText().isEmpty() || ValidaDatos.validarTarjeta(tftarjeta.getText()) == false) {
            tftarjeta.setText(null);
            tftarjeta.setStyle("-fx-prompt-text-fill: red;");
            tftarjeta.setPromptText("*Campo incorreto");
        }
        if (tfmes.getSelectionModel().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el mes", "Sistema de Pagos", JOptionPane.WARNING_MESSAGE);
        }
        if (caducoTarjeta() == true) {
            JOptionPane.showMessageDialog(null, "La tarjeta caduco", "Sistema de Pagos", JOptionPane.WARNING_MESSAGE);
        }
        if (tfanio.getText().isEmpty() || ValidaDatos.validarAnio(tfanio.getText()) == false) {
            tfanio.setText(null);
            tfanio.setStyle("-fx-prompt-text-fill: red;");
            tfanio.setPromptText("*Campo incorrecto");
        }
        if (pfcvv.getText().isEmpty() || ValidaDatos.validarCvv(pfcvv.getText()) == false) {
            pfcvv.setText(null);
            pfcvv.setStyle("-fx-prompt-text-fill: red;");
            pfcvv.setPromptText("*Campo incorrecto");
        }
        MultaJDBC multaPagada = new MultaJDBC();
        multaPagada.actualizarMulta(multa);
        regresaVentanaListaMultas();
    }

    private boolean caducoTarjeta() {
        String numeroMes = tfmes.getValue().substring(0, 2);
        int numeroEntero = Integer.valueOf(numeroMes);

        String anio = tfanio.getText();
        int anioEntero = Integer.valueOf(anio);

        if (numeroEntero < month && anioEntero == 21) {
            return true;
        } else {
            return false;
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
                Stage nuevoStage = (Stage) btnpagar.getScene().getWindow();
                nuevoStage.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public boolean encuentraBanco() {
        BinTarjetas bin = new BinTarjetas();

        String cadenaTarjeta = tftarjeta.getText().substring(0, 4);
        int enteroTarjeta = Integer.valueOf(cadenaTarjeta);

        if (bin.tipoBanco(enteroTarjeta) == "BBVA") {
            Icon icono = new ImageIcon(getClass().getResource("/img/BBVA.png"));
            JOptionPane.showMessageDialog(null, null, "Pago realizado con", JOptionPane.WARNING_MESSAGE, icono);
            return true;

        } else if (bin.tipoBanco(enteroTarjeta) == "BNMX") {
            Icon icono = new ImageIcon(getClass().getResource("/img/banamex.png"));
            JOptionPane.showMessageDialog(null, null, "Pago realizado con", JOptionPane.WARNING_MESSAGE, icono);
            return true;
        } else if (bin.tipoBanco(enteroTarjeta) == "HSBC") {
            Icon icono = new ImageIcon(getClass().getResource("/img/HSBC.png"));
            JOptionPane.showMessageDialog(null, null, "Pago realizado con", JOptionPane.WARNING_MESSAGE, icono);
            return true;

        } else if (bin.tipoBanco(enteroTarjeta) == "BNRT") {
            Icon icono = new ImageIcon(getClass().getResource("/img/banorte.png"));
            JOptionPane.showMessageDialog(null, null, "Pago realizado con", JOptionPane.WARNING_MESSAGE, icono);
            return true;

        } else if (bin.tipoBanco(enteroTarjeta) == "SANT") {
            Icon icono = new ImageIcon(getClass().getResource("/img/santander.png"));
            JOptionPane.showMessageDialog(null, null, "Pago realizado con", JOptionPane.WARNING_MESSAGE, icono);
            return true;

        } else if (bin.tipoBanco(enteroTarjeta).equals("INB")) {
            Icon icono = new ImageIcon(getClass().getResource("/img/inbursa.jpg"));
            JOptionPane.showMessageDialog(null, null, "Pago realizado con", JOptionPane.WARNING_MESSAGE, icono);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "La tarjeta no existe", "Error", JOptionPane.WARNING_MESSAGE);
        }
        return false;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfmes.getItems().addAll(mes);

    }

}
