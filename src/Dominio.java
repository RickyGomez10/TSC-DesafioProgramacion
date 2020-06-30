import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.*;

import static java.awt.Color.*;

public class Dominio extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int HEIGHTSCENE= 650;

    //Variables para movimiento del cubo
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Box box = new Box(100, 100, 100);
        PhongMaterial mat = new PhongMaterial();
        mat.setSpecularColor(Color.BLACK);
        mat.setDiffuseColor(Color.RED);
        box.setMaterial(mat);
        SmartGroup root = new SmartGroup();
        root.getChildren().add(box);

        //Separar escena en 3 partes
        VBox layout = new VBox(
                Cabecera(),
                escena3D(root),
                Pie()
        );

        Camera camera = new PerspectiveCamera();

        Scene scene = new Scene(layout, WIDTH, HEIGHTSCENE);

        /*
        root.translateXProperty().set(WIDTH/2);
        root.translateYProperty().set(HEIGHT/2);
        root.translateZProperty().set(-500);
        */
        primaryStage.setTitle("Dominio");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //Cabecera de escena
    private VBox Cabecera() {
        //Agregando elementos al ViewBox
        Label Encabezado = new Label("DOMINIO");
        Label instrucciones = new Label("Mueva el cubo con el mouse para ver el dominio");
        //Colocar tamaño a la fuente de los labels
        Encabezado.setFont(new Font(24));
        instrucciones.setFont(new Font(12));
        //Agregar elementos al ViewBox
        VBox controls = new VBox(10, Encabezado, instrucciones);
        //Posicion de los elementos
        controls.setAlignment(Pos.CENTER);
        //Padding de los elementos
        controls.setPadding(new Insets(10));
        return controls;

    }

    //Escena con cubo 3D
    private SubScene escena3D(SmartGroup sgroup) {
        SubScene scene3d = new SubScene(sgroup, WIDTH, HEIGHT - 100, true, SceneAntialiasing.BALANCED);
        scene3d.setFill(Color.WHITE);
        //Posicionar groupo
        sgroup.translateXProperty().set(WIDTH / 2);
        sgroup.translateYProperty().set(HEIGHT / 2);
        //Permitir rotar subscene con mouse
        initMouseControl(sgroup, scene3d);
        scene3d.setCamera(new PerspectiveCamera());
        return scene3d;
    }

    //Pie de pagina de la escena
    private VBox Pie() {
        //Agregando elementos al ViewBox
        Button btn_regresar = new Button("Regresar");
        btn_regresar.setOnAction(e -> {
            ((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
        });
        //Agregar elementos al ViewBox
        VBox controls2 = new VBox(10, btn_regresar);
        //Posicion de los elementos
        controls2.setAlignment(Pos.CENTER);
        //Padding de los elementos
        controls2.setPadding(new Insets(10));
        return controls2;

    }

    private void initMouseControl(SmartGroup root, SubScene scene) {
        Rotate xRotate;
        Rotate yRotate;
        root.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });
        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });
    }


    class SmartGroup extends Group {
        Rotate r;
        Transform t = new Rotate();


        void rotateByX(int ang) {
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }

        void rotateByY(int ang) {
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }
    }
}
