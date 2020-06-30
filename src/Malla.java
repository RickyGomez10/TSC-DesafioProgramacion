import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

public class Malla extends Application {
    //ALTURAS
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int HEIGHTSCENE= 650;
    private static final double MODEL_SCALE_FACTOR = 40;
    private static final double MODEL_X_OFFSET = 0;
    private static final double MODEL_Y_OFFSET = 0;
    private static final double MODEL_Z_OFFSET = WIDTH / 2;
    //Mover grupo
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    //Mesh
    private MeshView meshView = loadMeshView();

    //Puntos para el mallado
    private MeshView loadMeshView() {
        float[] points = {
                0, 0, 5,      //P0
                5, 0, 5,    //P1
                0, 5, 5,    //P2
                5, 5, 5,  //P3
                0, 0, 0,        //P4
                5, 0, 0,      //P5
                0, 5, 0,      //P6
                5, 5, 0     //P7
        };
        float[] texCoords = {
                0.25f, 0,       //T0
                0.5f, 0,        //T1
                0, 0.25f,       //T2
                0.25f, 0.25f,   //T3
                0.5f, 0.25f,    //T4
                0.75f, 0.25f,   //T5
                1, 0.25f,       //T6
                0, 0.5f,        //T7
                0.25f, 0.5f,    //T8
                0.5f, 0.5f,     //T9
                0.75f, 0.5f,    //T10
                1, 0.5f,        //T11
                0.25f, 0.75f,   //T12
                0.5f, 0.75f     //T13
        };
        int[] faces = {
                5, 1, 4, 0, 0, 3     //P5,T1 ,P4,T0  ,P0,T3
                , 5, 1, 0, 3, 1, 4    //P5,T1 ,P0,T3  ,P1,T4
                , 0, 3, 4, 2, 6, 7    //P0,T3 ,P4,T2  ,P2,T8 //este modificado P6,T7 POR P2,T8
                , 0, 3, 6, 7, 2, 8    //P0,T3 ,P6,T7  ,P2,T8
                , 1, 4, 0, 3, 2, 8    //P1,T4 ,P0,T3  ,P2,T8
                , 1, 4, 2, 8, 3, 9    //P1,T4 ,P2,T8  ,P3,T9
                , 5, 5, 1, 4, 3, 9    //P5,T5 ,P1,T4  ,P3,T9
                , 5, 5, 3, 9, 7, 10   //P5,T5 ,P3,T9  ,P7,T10
                , 4, 6, 5, 5, 7, 10   //P4,T6 ,P5,T5  ,P7,T10
                , 4, 6, 7, 10, 6, 11  //P4,T6 ,P7,T10 ,P6,T11 //este
                , 3, 9, 2, 8, 6, 12   //P3,T9 ,P2,T8  ,P6,T12
                , 3, 9, 6, 12, 7, 13  //P3,T9 ,P6,T12 ,P7,T13
                , 0, 3, 7, 10, 1, 4   //P0, T3 ,P7,T10, P1,T4
                , 0, 3, 7, 13, 1, 4   //P0, T3 ,P7,T13, P1,T4
                , 0, 3, 7, 13, 6, 7   //P0, T3 ,P7,T13, P6,T7
                , 0, 3, 7, 13, 6, 11  //P0, T3 ,P7,T13, P6,T11

        };

        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().setAll(points);
        mesh.getTexCoords().setAll(texCoords);
        mesh.getFaces().setAll(faces);

        return new MeshView(mesh);
    }

    private Group buildScene() {

        meshView.setScaleX(MODEL_SCALE_FACTOR);
        meshView.setScaleY(MODEL_SCALE_FACTOR);
        meshView.setScaleZ(MODEL_SCALE_FACTOR);
        meshView.drawModeProperty().set(DrawMode.LINE);

        return new Group(meshView);
    }

    @Override
    public void start(Stage primaryStage) {

        Group group = buildScene();
        //Mover grupo
        SmartGroup root = new SmartGroup();
        root.getChildren().add(group);

        //Separar escena en 2 pantallas
        VBox layout = new VBox(
                Cabecera(),
                CuboMallado(root),
                Pie()
        );
        //Escena
        Scene scene = new Scene(layout, WIDTH, HEIGHTSCENE);






        primaryStage.setTitle("Malla");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    //Encabezado de la escena
    private VBox Cabecera(){
        //Agregando elementos al ViewBox
        Label Encabezado = new Label("MALLADO");
        Label instrucciones = new Label("Mueva el cubo con el mouse para ver el mallado");
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
    //Cubo con mallado
    private SubScene CuboMallado(SmartGroup sgroup){
        SubScene scene3d = new SubScene(sgroup, WIDTH, HEIGHT-100, true, SceneAntialiasing.BALANCED);
        scene3d.setFill(Color.WHITE);
        //Posicionar groupo
        sgroup.translateXProperty().set(WIDTH/2);
        sgroup.translateYProperty().set(HEIGHT/2);
        //Permitir rotar subscene con mouse
        initMouseControl(sgroup, scene3d);
        scene3d.setCamera(new PerspectiveCamera());
        return scene3d;
    }
    //Pie de pagina de la escena
    private VBox Pie(){
        //Agregando elementos al ViewBox
        Button btn_regresar = new Button("Regresar");
        btn_regresar.setOnAction(e -> {
            ((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
        });
        //Agregar elementos al ViewBox
        VBox controls2 = new VBox(10, btn_regresar);
        //Posicion de los elementos
        controls2.setAlignment(Pos.CENTER);
        //Padding de los elementos
        controls2.setPadding(new Insets(10));
        return controls2;

    }
    //Mover grupo
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
