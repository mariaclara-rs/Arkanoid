package Principal.ArkanoidEstrutura;

import javafx.scene.layout.Pane;

public class BlocoVermelho extends AbstractBloco{
    
    public BlocoVermelho(int cx, int cy, int largura, int altura, Pane painel, int pontos) {
        super(cx, cy, largura, altura, painel, pontos);
    }
    
    @Override
    public void mover(){
        //void
    }
}
