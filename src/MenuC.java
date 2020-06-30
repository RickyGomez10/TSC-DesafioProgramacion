import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



import java.io.IOException;

public class MenuC {
    //Hacer click en imagen Dominio
    @FXML
    private void Dominio(MouseEvent event) throws IOException {
        //Abrir nuevo
        new Dominio().start(new Stage());
    }

    @FXML
    private void Malla(MouseEvent event) throws IOException {
        new Malla().start(new Stage());
    }

    @FXML
    private void Tabla(MouseEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vista/TablaConectividades.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Tabla de conectividades");
            //Colocar CSS
            String css = Menu.class.getResource("style/style.css").toExternalForm();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.println("No se pudo abrir nueva ventana");
        }

    }

    @FXML
    private void Modelo(MouseEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vista/Modelo.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Modelo");
            //Colocar CSS
            String css = Menu.class.getResource("style/style.css").toExternalForm();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.println("No se pudo abrir nueva ventana");
        }

    }

    @FXML
    private void MEF(MouseEvent event) throws IOException {
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vista/MEF.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("MEF");
        //Colocar CSS
        String css = Menu.class.getResource("style/style.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }catch(Exception e){
        System.out.println("No se pudo abrir nueva ventana");
    }


    }
}
