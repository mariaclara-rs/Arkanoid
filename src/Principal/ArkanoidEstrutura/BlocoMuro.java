package Principal.ArkanoidEstrutura;

import javafx.scene.layout.Pane;


public class BlocoMuro extends AbstractBloco{
    
    private int qtdColisoes;
    public BlocoMuro(int cx, int cy, int largura, int altura, Pane painel, int pontos) {
        super(cx, cy, largura, altura, painel, pontos);
        setQtdColisoes(0);
    }
    @Override
    public void mover(){
        //void
    }

    public int getQtdColisoes() {
        return qtdColisoes;
    }

    public void setQtdColisoes(int qtdColisoes) {
        this.qtdColisoes = qtdColisoes;
    }
    public void colidiu() {
        qtdColisoes++;
    }
}
