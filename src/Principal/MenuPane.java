package Principal;

import Principal.ArkanoidEstrutura.ArkanoidGame;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import static Principal.Menu.LARGURA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Principal.Menu.ALTURA;
import javafx.scene.image.Image;

public class MenuPane extends Pane {
    private Button btN1;
    private Button btN2;
    private Button btAjuda;
    
    public MenuPane() {
        super();
        Image image;
        
        try {
            image = new Image(new FileInputStream("src\\Principal\\img\\backgroundMenu.jpg"));
            BackgroundImage backgroundimage     =   new BackgroundImage(image,  
                                                BackgroundRepeat.NO_REPEAT,  
                                                BackgroundRepeat.NO_REPEAT,  
                                                BackgroundPosition.CENTER,  
                                                BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage); 
            this.setBackground(background); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArkanoidGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        apresentaBt();
    }
    
    //Muda a aparencia dos botoes
    private void estilizaBt(Button bt) {
        String btPadrao="-fx-background-color: rgba(255,255,255,0.7); -fx-font-size: 14px; -fx-text-fill: black;";
        String btHover="-fx-background-color: black; -fx-font-size: 14px; -fx-text-fill: white;";
        bt.setStyle(btPadrao);
        bt.setOnMouseEntered(e -> bt.setStyle(btHover));
        bt.setOnMouseExited(e -> bt.setStyle(btPadrao));
    }
    
    //Mostra os botoes na tela
    private void apresentaBt(){
        btN1 = new Button("Normal");
        btN2 = new Button("Difícil");
        btAjuda = new Button("Instruções");
        estilizaBt(btN1); estilizaBt(btN2); estilizaBt(btAjuda);
        btN1.setPrefSize(100, 50);
        btN2.setPrefSize(100, 50);
        btAjuda.setPrefSize(100, 50);
        VBox painelBt = new VBox(); 
        painelBt.setSpacing(5); 
        painelBt.setAlignment(Pos.CENTER); 
        painelBt.setTranslateX(LARGURA/2 - 50);
        painelBt.setTranslateY(ALTURA/2 - 75);
        painelBt.getChildren().addAll(btN1, btN2, btAjuda);
        getChildren().add(painelBt);
    }
    
    protected Button getBtN1() {
        return btN1;
    }
    
    protected Button getBtN2() {
        return btN2;
    }

    protected void setBtN1(Button bt) {
        this.btN1 = bt;
    }
    
    protected void setBtN2(Button bt) {
        this.btN2 = bt;
    }

    protected Button getBtAjuda() {
        return btAjuda;
    }

    protected void setBtAjuda(Button btAjuda) {
        this.btAjuda = btAjuda;
    }
}
