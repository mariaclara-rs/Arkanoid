package Principal.Janelas;

import Principal.Menu;
import java.io.FileNotFoundException;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.scene.media.MediaPlayer;

public class Informacoes extends Application{
    private static Stage stage;
    private int estado, score;
    private AnimationTimer timer;
    private String texto;
    private MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage){

        InformacoesPane ip = new InformacoesPane();
        Scene scene = new Scene(ip, 450, 350);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        setStage(stage);
        verificaBt(ip, scene);

    }
    
    //Verifica se algum dos botões foi clicado
    public void verificaBt(InformacoesPane ip, Scene scene) {
        ip.addLabel();
        ip.getL().setText(texto);
        ip.estilizaLabel();
        //Verifica se foi pressionado P novamente e volta a jogar
        if(estado == 0){
            ip.setOnKeyPressed(k->{
            if(k.getCode()==KeyCode.P){
                fechar();
                timer.start();
                mediaPlayer.play();
            }
         });
        }
        else{
            ip.addLabelScore();
            ip.getLscore().setText("PONTOS: "+score);
            ip.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-font-family: Consolas; -fx-text-fill: black; -fx-padding: 5 0 0 15");
        }
        
        //Volta para o menu principal
        ip.getBtMenu().setOnMouseClicked((MouseEvent e)->{  
            Menu menu = new Menu();
            menu.start(new Stage());
            mediaPlayer.stop();
            fechar();
            NovoNivel.fechar();
        });
        
        //Reinicia o jogo na mesma dificuldade
        ip.getBtReplay().setOnMouseClicked((MouseEvent e)->{ 
            NovoNivel.fechar();
            NovoNivel N = new NovoNivel(NovoNivel.getNivel());
            try {
                N.start(new Stage());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            fechar();
        });
    }

    public void setTimer(AnimationTimer timer){
        this.timer = timer;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void fechar() {
        stage.close();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Informacoes.stage = stage;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

}
