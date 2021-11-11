
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author DevSolutions
 */
public class SistemaMultasPSP0 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("MultasVistaDetalles.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        //MultasVistaDetallesController controlador = (MultasVistaDetallesController) loader.getController();
        //controlador.ap = (Pane) root;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestión Vehicular");
        stage.setResizable(false);
        stage.getIcons().add(new Image(SistemaMultasPSP0.class.getResourceAsStream("/img/SistemaMultas-Logo.png")));
        stage.show();
        
        
        //AnchorPane root = (AnchorPane) loader.load();
        //FXMLFormadePagoController controlador = (FXMLFormadePagoController) loader.getController();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
