package Principal.ArkanoidEstrutura;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Bolinha extends GameObject {
    private int passoX, passoY;
    private int velInicial;

    public Bolinha(int cx, int cy, int raio, int velocidade, Pane painel) {
        super(cx, cy, raio, painel);
        this.passoX = this.passoY = velocidade;
        this.velInicial = velocidade;
        getFigura().setFill(Paint.valueOf("#0000FF"));
    }
    
    //Verifica se a bolinha colidiu com algum objeto
    public boolean verColisao(GameObject objeto) {
       return getFigura().getBoundsInParent().intersects(objeto.getFigura().getBoundsInParent());
    }
    @Override
    public Circle getFigura() {
        return (Circle) super.getFigura();
    }

    //Move a bolinha
    @Override
    public void mover() {
        setCx(getCx()+passoX);
        setCy(getCy()+passoY);
        this.getFigura().setCenterX(getCx());
        this.getFigura().setCenterY(getCy());
        if(getCx()<=0 || getCx()>=getPainel().getWidth())
            mudarDirecaoX();
        if(getCy()<=0 || getCy()>=getPainel().getHeight())
            mudarDirecaoY();
    }

    public void mudarDirecaoY() {
        passoY*=-1;
    }
    public void mudarDirecaoX() {
        passoX*=-1;
    }
    
    //Volta a velocidade da bolinha com base no nivel escolhido
    public void normalizar() {
        if(passoX>=0)
            passoX = velInicial;
        else
            passoX = velInicial*-1;
        if(passoY>=0)
            passoY = velInicial;
        else
            passoY = velInicial*-1;
    }

    public int getPassoX() {
        return passoX;
    }

    public void setPassoX(int passoX) {
        this.passoX = passoX;
    }

    public int getPassoY() {
        return passoY;
    }

    public void setPassoY(int passoY) {
        this.passoY = passoY;
    }
    
    //Aumenta a velocidade da bolinha
    public void acelera(int vezes) {
       setPassoX(getPassoX()*vezes);
       setPassoY(getPassoY()*vezes);
    }

    //Verifica se a bolinha passou do fundo maximo
    public boolean ultrapassouLimite() {
        return this.getFigura().getCenterY() >= this.getPainel().getHeight();
    }
}
