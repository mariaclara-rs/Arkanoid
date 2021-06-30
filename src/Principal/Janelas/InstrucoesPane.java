package Principal.Janelas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;


public class InstrucoesPane extends Pane{
    private Button btMenu;
    
    public InstrucoesPane() {
        super();
        Image image;
        try {
            image = new Image(new FileInputStream("src\\Principal\\img\\backgroundInstrucoes.jpg"));
            BackgroundImage backgroundimage = new BackgroundImage(image,  
                                                                BackgroundRepeat.NO_REPEAT,  
                                                                BackgroundRepeat.NO_REPEAT,  
                                                                BackgroundPosition.CENTER,  
                                                                BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage); 
            this.setBackground(background); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InstrucoesPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        btMenu = new Button("Menu");
        estilizaBt(btMenu);
        btMenu.setPrefSize(70, 30);
        getChildren().add(btMenu);
    }
    
    //Muda a aparencia dos botoes
    private void estilizaBt(Button bt) {
        String btPadrao="-fx-background-color: rgba(255,255,255,0.7); -fx-text-fill: black;";
        String btHover="-fx-background-color: black; -fx-text-fill: white;";
        bt.setStyle(btPadrao);
        bt.setOnMouseEntered(e -> bt.setStyle(btHover));
        bt.setOnMouseExited(e -> bt.setStyle(btPadrao));
    }

    protected Button getBtMenu() {
        return btMenu;
    }

    protected void setBtMenu(Button menu) {
        this.btMenu = menu;
    }
    
}
