package Principal.Janelas;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import Principal.ArkanoidEstrutura.ArkanoidGame;

import javafx.stage.Stage;

public class NovoNivel extends Application {
    private static Stage stage;
    private static final int ALTURA = 670, LARGURA = 653;
    private static int nivel;

    public NovoNivel(int nivel) {
        NovoNivel.nivel = nivel;
    }

    //Inicia uma nova janela do nivel selecionado
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        ArkanoidGame ag = new ArkanoidGame();
        Scene scene = new Scene(ag, LARGURA, ALTURA);
        primaryStage.setTitle("ARKANOID");
        primaryStage.setScene(scene);
        setStage(primaryStage);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getStage() {
        return stage;
    }

    public static int getNivel() {
        return nivel;
    }

    public static void fechar() {
        stage.close();
    }

    public static void setStage(Stage stage) {
        NovoNivel.stage = stage;
    }

    
}
