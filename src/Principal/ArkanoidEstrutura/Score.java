package Principal.ArkanoidEstrutura;

public class Score {
    private int pontos;

    public Score(int pontos) {
        if(pontos>=0)
            this.pontos = pontos;
    }
    public Score() {
        this(0);
    }
    public void somar(int pontos) {
        this.pontos+=pontos;
    }
    
    public int getPontos() {
        return pontos;
    }
    public void zerar() {
        pontos = 0;
    }
}
