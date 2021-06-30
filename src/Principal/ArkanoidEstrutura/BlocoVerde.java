package Principal.ArkanoidEstrutura;

import javafx.scene.layout.Pane;

public class BlocoVerde extends AbstractBloco {
    private int qtdColisoes;
    private boolean move; 
    private int passo, altInicial;
    
    
    public BlocoVerde(int cx, int cy, int largura, int altura, Pane painel, int pontos, int qtdColisoes) {
        super(cx, cy, largura, altura, painel, pontos);
        altInicial = cy;
        this.qtdColisoes = qtdColisoes;
        this.move = false;
        passo = 2;
    }

    //Move o bloco da direita pra esquerda e vice versa
    @Override
    public void mover(){
        setCx(getCx()+passo);
        getFigura().setX(getCx());
        if((getCx()+getFigura().getWidth())==getPainel().getWidth() || getCx()==0)
            passo*=-1;
    }
    
    public int getQtdColisoes() {
        return qtdColisoes;
    }

    public void setQtdColisoes(int qtdColisoes) {
        this.qtdColisoes = qtdColisoes;
    }
    
    public void descer(int altura) {
        setCy(altura);
        getFigura().setY(getCy());
    }
    
    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public int getAltInicial() {
        return altInicial;
    }
    
}
