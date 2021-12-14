/**
 * FXML Controller class
 *
 * @author DevSolutions
 */
import DTOModel.Multa;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLFormadePagoController implements Initializable {
    
    @FXML
    private RadioButton rbtnpaypal;
    @FXML
    private RadioButton rbtntc;
    @FXML
    private Button btnpago;
    @FXML
    private ToggleGroup tgselected;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button btnCancelar;
    
    public Multa multa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
        
    }

    
    
    @FXML
    private void seleccionaPago(){
        if(rbtnpaypal.isSelected()){
            //JOptionPane.showMessageDialog(null, "PayPal","Sistema de Pagos",JOptionPane.WARNING_MESSAGE);
            try{
	        
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPayPal.fxml"));
	    AnchorPane root = (AnchorPane)loader.load();	
	        	
	    FXMLPayPalController controlador = (FXMLPayPalController)loader.getController();
            controlador.setMulta(multa);
            controlador.setLbtotal(String.valueOf(multa.getPrecio()));
	    ap.getChildren().clear();
	    ap.getChildren().add(root);
			      
	    } catch (IOException e) {

		e.printStackTrace(System.out);
	    }
        }else if(rbtntc.isSelected()){
            try{
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPagosVista.fxml"));
	    AnchorPane root = (AnchorPane)loader.load();   	
	    FXMLPagosVistaController controlador = (FXMLPagosVistaController)loader.getController();
            controlador.setMulta(multa);
            controlador.getLbmonto().setText("$ " + Float.toString(multa.getPrecio()));
            
	    ap.getChildren().clear();
	    ap.getChildren().add(root);
			      
	    } catch (IOException e) {
		e.printStackTrace(System.out);
	    }
        }
        
    }
   
    
    @FXML
    public void CancelarPago(){
        try{
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("MultasVistaDetalles.fxml"));
	    AnchorPane root = (AnchorPane)loader.load();
	          	
	    MultasVistaDetallesController controlador = (MultasVistaDetallesController)loader.getController();
            
            controlador.placaStage1(multa.getPlaca());
            
	    ap.getChildren().clear();
	    ap.getChildren().add(root);
			      
	    } catch (IOException e) {
		e.printStackTrace(System.out);
	    }
    }
    
    
}
