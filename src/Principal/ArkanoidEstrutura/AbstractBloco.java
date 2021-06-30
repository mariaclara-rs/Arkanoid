package Principal.ArkanoidEstrutura;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

abstract public class AbstractBloco extends GameObject {
    //não precisa implementar o método mover porque a classe é abstrata e ele vem herdado de GameObject
    private final int pontos;
    
    public AbstractBloco(final int cx, final int cy, final int largura, final int altura, final Pane painel, final int pontos) {
        super(cx, cy, largura, altura, painel);
        this.pontos = pontos;
    }
    
    public int getPontos() {
        return pontos;
    }

    @Override
    public Rectangle getFigura() {
        return (Rectangle) super.getFigura();
    }
}
