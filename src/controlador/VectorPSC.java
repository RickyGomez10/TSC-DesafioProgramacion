package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VectorPSC {

    @FXML
    private void Atras(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/MatrizU.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setTitle("MEF - DEFINICIÓN DE COMPONENTES");
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        } catch(Exception e) {
            System.out.println("No se pudo abrir nueva ventana");
        }

    }

    @FXML
    private void Cerrar(ActionEvent event) throws IOException{
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
