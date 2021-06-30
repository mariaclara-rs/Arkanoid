package Principal;

import Principal.Janelas.Instrucoes;
import Principal.Janelas.NovoNivel;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;

public class Menu extends Application {
    private static Stage stage;
    protected static final int ALTURA = 670;
    protected static final int LARGURA = 653;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        
        MenuPane mn = new MenuPane();
        Scene scene = new Scene(mn, LARGURA, ALTURA);

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
        //Abre uma nova janela do nivel normal
        mn.getBtN1().setOnMouseClicked((MouseEvent e) -> {
            NovoNivel N1 = new NovoNivel(1);
            try {
                N1.start(new Stage());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            fechar();
        });
        //Abre uma nova janela do nivel dificil
        mn.getBtN2().setOnMouseClicked((MouseEvent e) -> {
            NovoNivel N2 = new NovoNivel(2);
            try {
                N2.start(new Stage());
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            fechar();
        });
        //Abre uma nova janela de instrucoes
        mn.getBtAjuda().setOnMouseClicked((MouseEvent e) ->{
            Instrucoes instrucoes = new Instrucoes();
            fechar();
            instrucoes.start(new Stage());

        });
        
    }

    public void fechar() {
        stage.close();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Menu.stage = stage;
    }

    public int getLargura() {
        return LARGURA;
    }
    
}
