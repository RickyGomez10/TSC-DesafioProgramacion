package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


public class TablaConectividadesC implements Initializable {


    @FXML private TableView<Elemento> tableView;
    @FXML private TableColumn<Elemento, String> Elemento;
    @FXML private TableColumn<Elemento, String> uno;
    @FXML private TableColumn<Elemento, String> dos;
    @FXML private TableColumn<Elemento, String> tres;
    @FXML private TableColumn<Elemento, String> cuatro;
    @FXML private Button Cerrar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Elemento.setCellValueFactory(new PropertyValueFactory<Elemento, String>("Elemento"));
        uno.setCellValueFactory(new PropertyValueFactory<Elemento, String>("Nodo1"));
        dos.setCellValueFactory(new PropertyValueFactory<Elemento, String>("Nodo2"));
        tres.setCellValueFactory(new PropertyValueFactory<Elemento, String>("Nodo3"));
        cuatro.setCellValueFactory(new PropertyValueFactory<Elemento, String>("Nodo4"));

        tableView.setItems(getElementos());
    }
    public ObservableList<Elemento> getElementos(){
        ObservableList<Elemento> elementos = FXCollections.observableArrayList();
        elementos.add(new Elemento("1","3", "6", "7", "1"));
        elementos.add(new Elemento("2","6", "4", "8", "7"));
        elementos.add(new Elemento("3","7", "2", "1", "5"));
        elementos.add(new Elemento("4","8", "5", "7", "4"));
        elementos.add(new Elemento("5","4", "1", "5", "7"));
        elementos.add(new Elemento("6","6", "1", "4", "7"));

        return elementos;
    }
    @FXML
    public void cerrar(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}
