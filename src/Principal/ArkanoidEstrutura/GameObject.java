package Principal.ArkanoidEstrutura;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GameObject implements IGame{
    private int cx, cy;
    private Shape figura; 
    private Pane painel;
    
    //Construtor de bloco
    public GameObject(int cx, int cy, int largura, int altura, Pane painel) {
        this.cx = cx; this.cy = cy;
        this.figura = new Rectangle(cx, cy, largura, altura);
        this.painel = painel;
    }

    //Construtor da bolinha
    public GameObject(int cx, int cy, int raio, Pane painel) {
        this.cx = cx; this.cy = cy;
        this.figura = new Circle(cx, cy, raio);
        this.painel = painel;
    }

    //Construtor da raquete
    public GameObject(int cx, int cy, Pane painel) {
        this.painel = painel;
        this.figura = new Rectangle(cx, cy, 110, 15);
        this.figura.setStroke(Color.BLUE);
        this.figura.setFill(Color.BLUE.deriveColor(1, 3, 1, 0.3));
    }  

    public Shape getFigura() {
        return figura;
    }

    public Pane getPainel() {
        return painel;
    }

    @Override
    public void mover() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }
    
}
        
