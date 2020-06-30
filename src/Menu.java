import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Obtener FXML
        Parent root = FXMLLoader.load(getClass().getResource("vista/Menu.fxml"));
        //Titulo de la ventana
        primaryStage.setTitle("Menu");
        //Obtener CSS
        String css = Menu.class.getResource("style/style.css").toExternalForm();
        //Crear ventana
        Scene scene = new Scene(root);
        //Colocar css a la ventana
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
