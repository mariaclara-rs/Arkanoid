package Principal.ArkanoidEstrutura;

import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Raquete extends GameObject {

    public Raquete(int cx, int cy, Pane painel) {
        super(cx, cy, painel);
    }
    
    @Override
    public Rectangle getFigura() {
        return (Rectangle) super.getFigura();
    }

    //Move a raquete com o movimento do mouse
    @Override
    public void mover() {
        super.getPainel().setOnMouseMoved(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if(event.getX()>=(getFigura().getWidth()/2) && event.getX()<=(getPainel().getWidth() - getFigura().getWidth()/2))
                    setCx((int) (event.getX()-getFigura().getWidth()/2));
                    getFigura().setX(getCx());
            }
        });
    }
    
    //Impede o reconhecimento do mouse para mecher a raquete enquanto esta pausado
    public void pausar() {
        super.getPainel().setOnMouseMoved(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
            }
        });
    }
    
    //Aumenta o tamanho da raquete
    public void aumentar(int px) {
        getFigura().setWidth(getFigura().getWidth() + px);
        getFigura().setStroke(Color.BLACK);
        getFigura().setFill(Color.BLACK.deriveColor(1, 3, 1, 0.3));
    }
    
    //Volta a raquete ao tamanho original
    public void normalizar() {
        getFigura().setWidth(110);
        getFigura().setStroke(Color.BLUE);
        getFigura().setFill(Color.BLUE.deriveColor(1, 3, 1, 0.3));
    }
}
