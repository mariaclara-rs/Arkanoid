package Principal.Janelas;

import Principal.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class Instrucoes extends Application{
    private static Stage stage;
    protected static final int ALTURA = 670;
    protected static final int LARGURA = 653;
    
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage){
        InstrucoesPane ip = new InstrucoesPane();
        Scene scene = new Scene(ip, LARGURA, ALTURA);
        stage.setTitle("Instruções");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
        ip.getBtMenu().setOnMouseClicked((MouseEvent e) ->{
            Menu mn = new Menu();
            mn.start(new Stage());
            fechar();
        });
    }
    public void fechar() {
        stage.close();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Instrucoes.stage = stage;
    }
}
