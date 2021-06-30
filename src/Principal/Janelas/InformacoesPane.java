
package Principal.Janelas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class InformacoesPane extends VBox{
    
    private Button btMenu;
    private Button btReplay;
    private Label L, Lscore;
    
    public InformacoesPane() {
       super();
       try {
            Image image = new Image(new FileInputStream("src\\Principal\\img\\backgroundPopup.png"));
                        BackgroundImage backgroundimage = new BackgroundImage(image,  
                                                BackgroundRepeat.NO_REPEAT,  
                                                BackgroundRepeat.NO_REPEAT,  
                                                BackgroundPosition.CENTER,  
                                                BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage); 
            this.setBackground(background); 

        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformacoesPane.class.getName()).log(Level.SEVERE, null, ex);
        }
       inicializa();

    }

    //Inicia a tela de pause/fim de jogo
    private void inicializa() {
        L = new Label(""); 
        Lscore = new Label("");
        btMenu = new Button("Menu");  btMenu.setPrefSize(200, 36); estilizaBt(btMenu);
        btReplay = new Button("Recomeçar");  btReplay.setPrefSize(200, 36); estilizaBt(btReplay);
        setAlignment(Pos.CENTER); //alinhamento centralizado
        setPrefWidth(450); setPrefHeight(350); //seta o tamanho do popup
        setSpacing(32); //espaçamento entre os componentes
        getChildren().addAll(btMenu, btReplay);
    }
    
    //Muda a aparencia dos botoes
    private void estilizaBt(Button bt) {
        String btPadrao="-fx-background-color: rgba(255,255,255,0.7); -fx-font-size: 14px; -fx-text-fill: black;";
        String btHover="-fx-background-color: black; -fx-font-size: 14px; -fx-text-fill: white;";
        bt.setStyle(btPadrao);
        bt.setOnMouseEntered(e -> bt.setStyle(btHover));
        bt.setOnMouseExited(e -> bt.setStyle(btPadrao));
    }
    
    protected void addLabel() {
        getChildren().add(L);
    }
    
    protected void addLabelScore() {
        getChildren().add(Lscore);
    }
    
    protected Button getBtMenu() {
        return btMenu;
    }
    
    protected void setBtMenu(Button btMenu) {
        this.btMenu = btMenu;
    }

    protected Button getBtReplay() {
        return btReplay;
    }

    protected void setBtReplay(Button btReplay) {
        this.btReplay = btReplay;
    }

    protected Label getL() {
        return L;
    }
    protected void estilizaLabel() {
        L.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-font-family: Consolas; -fx-text-fill: white; -fx-padding: 5 0 0 15");
    }
    protected Label getLscore() {
        return Lscore;
    }

    protected void setLscore(Label Lscore) {
        this.Lscore = Lscore;
    }
    
    
}
    

