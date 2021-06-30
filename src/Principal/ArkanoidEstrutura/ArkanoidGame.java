package Principal.ArkanoidEstrutura;

import Principal.Janelas.NovoNivel;
import Principal.Janelas.Informacoes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;


public class ArkanoidGame extends Pane {
    private int vidas; //quantidade de vidas == quantidade de bolinhas em jogo
    private int estado; //pause=0, em funcionamento=1
    private List <AbstractBloco> blocos;
    private Raquete raquete;
    private List <Bolinha> b;
    private Score score;

    int m = 5; //espaçamento entre os blocos
    int indGol; //indice a ser sorteado para definir tipo/cor do gol/bloco
    String texto; //texto a ser apresentado "Você ganhou", "Você perdeu" ou "[p] para despausar"
    Media soundGanhou, soundRaquete, fundo, soundBloco;
    MediaPlayer mediaPlayer, mediaPlayerFundo;
    File pastaGols = new File("src\\Principal\\img\\gols");
    File imgGols[] = pastaGols.listFiles();
    public ArkanoidGame() {
        super();
        Image image;
        try {
            //sons a serem reproduzidos em diferentes situações
            soundGanhou = new Media(new File("src\\Principal\\audios\\audioGanhou.mp3").toURI().toString());
            soundRaquete = new Media(new File("src\\Principal\\audios\\raquete.mp3").toURI().toString());
            fundo = new Media(new File("src\\Principal\\audios\\SONS_DE_ESTADIO.mp3").toURI().toString());
            soundBloco = new Media(new File("src\\Principal\\audios\\sfxGols.mp3").toURI().toString());
           
            //definindo o background
            image = new Image(new FileInputStream("src\\Principal\\img\\backgroundVerde.jpg"));
            BackgroundImage backgroundimage     =   new BackgroundImage(image,  
                                                BackgroundRepeat.NO_REPEAT,  
                                                BackgroundRepeat.NO_REPEAT,  
                                                BackgroundPosition.CENTER,  
                                                BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage); 
            this.setBackground(background); 
            Platform.runLater(()->iniciar());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArkanoidGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    private void iniciar() {
        vidas = 1; //inicia com 1 vida = num de bolinhas
        estado = 1; //estado de funcionamneto
        texto = "";
        score = new Score();
        b = new ArrayList(); // Lista de bolinhas
        blocos = new ArrayList(); // Lista de blocos
        raquete = new Raquete((int) (this.getWidth()/2),(int) (this.getHeight()-25),this); // raquete
        getChildren().add(raquete.getFigura());
        exibeGols(blocos); //função que sorteia os blocos a serem inseridos e os adiciona na tela
        adicionaBolinha();        
        jogar();  
    }
    
    private void jogar() {
        mediaPlayerFundo = new MediaPlayer(fundo); 
        mediaPlayerFundo.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayerFundo.setVolume(0.1);
        mediaPlayerFundo.play();
        Label Lpontos = new Label();
        Label Lvidas = new Label();
        Lpontos.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-font-family: Consolas; -fx-text-fill: white; -fx-padding: 5 0 0 15");
        Lvidas.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-font-family: Consolas; -fx-text-fill: white; -fx-padding: 25 0 0 15");
        getChildren().add(Lpontos);
        getChildren().add(Lvidas);

        AnimationTimer timer = new AnimationTimer() {
            
            @Override
            public void handle(long now) { 
                texto = "";
                controlaBolinha();
                raquete.mover();
                moveBlocoVerde();
                vidas = b.size();
                //verifica fim de jogo
                if(finalizar()){
                    excluiBolinhas(); stop();
                    Informa();
                }

                Lpontos.setText("PONTOS: " + score.getPontos());
                Lvidas.setText("VIDAS: " + vidas);
        
                for(Bolinha bol:b) {
                    for(int i=0; i<blocos.size(); i++) {
                        
                        //Verifica se bolinha tocou em algum dos blocos
                        if(bol.verColisao(blocos.get(i))) {
 
                            mediaPlayer = new MediaPlayer(soundBloco); 
                            mediaPlayer.play();
                            //sfxGols
                            score.somar(blocos.get(i).getPontos()); //Incrementa pontos toda vez que a bolinha toca em algum dos blocos
                            
                            //Verifica se tocou no bloco branco
                            if(blocos.get(i) instanceof BlocoBranco) {
                                if(Math.abs(bol.getPassoX())==2 || Math.abs(bol.getPassoX())==3){ //Verifica velocidade da bolinha
                                    bol.acelera(2); //Aumenta o dobro da sua velocidade
                                    new Thread(()->{ //Loop que mante 15 segundos de efeito na bolinha que tocou no bloco
                                        try{                          
                                            Thread.sleep(15000);
                                            bol.normalizar(); //Volta velocidade nomal
                                        }catch(Exception e){    }                 
                                    }).start();
                                }

                                //Remove o bloco tocado
                                getChildren().remove(blocos.get(i).getFigura());
                                blocos.remove(blocos.get(i));  
                            } //Fim da verificação do bloco branco

                            //Verifica se tocou no bloco verde
                            else if(blocos.get(i) instanceof BlocoVerde){
                                ((BlocoVerde)blocos.get(i)).setQtdColisoes(((BlocoVerde)blocos.get(i)).getQtdColisoes()+1);
                                
                                if(((BlocoVerde)blocos.get(i)).getQtdColisoes() == 1) { //Verifica se a quantidade de colisões 
                                    //Move o bloco tocado para perto da raquete caso ele tenha colisões a receber
                                    ((BlocoVerde) blocos.get(i)).descer((int) ((int)raquete.getFigura().getY()-raquete.getFigura().getHeight()-120)); 
                                    ((BlocoVerde)blocos.get(i)).setMove(true); 
                                } else if(blocos.get(i).getCy()>((BlocoVerde)blocos.get(i)).getAltInicial()+blocos.get(i).getFigura().getWidth()){
                                    //Remove o bloco tocado caso ele ja não tenha mais colisões a receber
                                    getChildren().remove(blocos.get(i).getFigura());
                                    blocos.remove(blocos.get(i));
                                }
                            } //Fim da verificação do bloco verde

                            //Verifica se tocou no bloco azul
                            else if(blocos.get(i) instanceof BlocoAzul){
                                    getChildren().remove(blocos.get(i).getFigura());
                                    blocos.remove(blocos.get(i));
                                    if(b.size() < 3){
                                        adicionaBolinha();
                                        b.get(b.size()-1).setPassoX(b.get(0).getPassoX()*-1);
                                    }
                                }//Fim da verificação do bloco azul
                            //verifica se tocou bloco vermelho
                            else if(blocos.get(i) instanceof BlocoVermelho){
                                getChildren().remove(blocos.get(i).getFigura());
                                blocos.remove(blocos.get(i));
                                bolinhaMagica(bol);
                            }//fim da verificação do bloco vermelho
                            else if(blocos.get(i) instanceof BlocoMuro){
                                controlaMuro(((BlocoMuro)blocos.get(i)));
                            } 
                            else{
                                getChildren().remove(blocos.get(i).getFigura());
                                blocos.remove(blocos.get(i));   
                            }

                            //Muda a direção da bolinha sempre que toca em algum bloco
                            bol.mudarDirecaoY();
    
                        }
                    }
                }

            }
 
        };
        timer.start();
        pausar(timer);
        encerrar();
    }

    //Detecta quando o jogador perdeu todas as suas vidas e encerra o jogo
    private boolean finalizar() {
        if(vidas==0)
            texto = "Você perdeu =(";
        else if(vidas>0 && blocos.size()==0){ //verifica
            texto = "Você ganhou =)";
            mediaPlayer = new MediaPlayer(soundGanhou); 
            mediaPlayer.play();
        }
        else
            return false;
        return true;
    }
    
    //Fecha o jogo
    private void encerrar() {    
        this.getScene().getWindow().setOnCloseRequest(e->{System.exit(0);});
    }
    
    //Pausa todas os objetos e suas animacoes
    private void pausar(AnimationTimer timer) {
        getScene().setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.P){
                estado = estado==0? 1: 0;
                if(estado==0){

                    timer.stop(); raquete.pausar();//necessário pois a raquete é controlada em outra class pelo mouse
                    texto = "Pressione [P] para despausar";
                    Informa(timer);
                    estado = 1;
                }
            }
        });
    }
    
    //Abre a janela de fim de jogo
    private void Informa() {
            mediaPlayerFundo.stop();
            Informacoes info = new Informacoes();
            info.setEstado(estado);
            info.setScore(score.getPontos());
            info.setTexto(texto);
            info.setMediaPlayer(mediaPlayerFundo);
            info.start(new Stage());
    }
    
    //Abre a janela de pause
    private void Informa(AnimationTimer timer) {
            mediaPlayerFundo.stop();
            Informacoes info = new Informacoes();
            info.setEstado(estado);
            info.setScore(score.getPontos());
            info.setTimer(timer);
            info.setTexto(texto);
            info.setMediaPlayer(mediaPlayerFundo);
            info.start(new Stage());
    }
    
    //Exclui uma bolinha do array de bolinhas
    private void excluiBolinhas() {
        for(Bolinha bol: b){
            b.remove(bol);
            getChildren().remove(bol.getFigura());
        }
    }
    
    //Tranforma a bolinha em semi-transparente
    private void bolinhaMagica(Bolinha b) {
        Image image;
        try {
            image = new Image(new FileInputStream("src\\Principal\\img\\bolaMagica.png"));
            ImagePattern image_pattern = new ImagePattern(image);
            b.getFigura().setFill(image_pattern);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArkanoidGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void controlaMuro(BlocoMuro bloco){
        bloco.colidiu();
        if(bloco.getQtdColisoes() == 1){
            Image image;
            try {
                image = new Image(new FileInputStream("src\\Principal\\img\\muroDestrancado.png"));
                ImagePattern image_pattern = new ImagePattern(image);
                bloco.getFigura().setFill(image_pattern);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArkanoidGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            raquete.aumentar(25);
            getChildren().remove(bloco.getFigura());
            blocos.remove(bloco);
            new Thread(()->{
                try {
                    Thread.sleep(8000);
                    raquete.normalizar();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ArkanoidGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
        }
    }
    private void moveBlocoVerde() {
        for(AbstractBloco b:blocos)
        if(b instanceof BlocoVerde) { //Verifica se o bloco na posição é verde
           if(((BlocoVerde)b).isMove()){
               ((BlocoVerde)b).mover(); 
         }
       }
    }

    private void controlaBolinha() {
        for(Bolinha bol:b) {
            bol.mover(); //Mantém a bolinha se movendo
            if(bol.ultrapassouLimite()) { //Verifica se a bolinha tocou no chão e decrementa vida caso tenha
                b.remove(bol);
                getChildren().remove(bol.getFigura());
                
            }
            //System.out.println(raquete.getFigura().getY());
            if(bol.verColisao(raquete)){ //Verifica se houve colisão da bolinha com a raquete e muda sua direção
                bol.mudarDirecaoY();
                mediaPlayer = new MediaPlayer(soundRaquete); 
                mediaPlayer.play();
            }
        } 
    }

    private void exibeGols(List<AbstractBloco> blocos) {
        Image image;
        ImagePattern image_pattern;
        int col;
        try {
            //primeiro bloco para referência 
            image = new Image(new FileInputStream("src\\Principal\\img\\gols\\"+imgGols[2].getName()));
            image_pattern = new ImagePattern(image);
            blocos.add(new BlocoBranco(10, 50, 75, 50, this, 10));
            blocos.get(0).getFigura().setFill(image_pattern); 
            getChildren().add(blocos.get(0).getFigura());

            //Faz o espaçamento entre as imagens
            for(int linha = 0; linha < 5; linha++){
                col=1;
                if(linha > 0){
                    image = new Image(new FileInputStream("src\\Principal\\img\\gols\\"+imgGols[2].getName()));
                    image_pattern = new ImagePattern(image);
                    blocos.add(new BlocoBranco(10, (int) (blocos.get(blocos.size()-1).getFigura().getY()+50+2*m), 75, 50, this, 10));
                    blocos.get(blocos.size()-1).getFigura().setFill(image_pattern); 
                    getChildren().add(blocos.get(blocos.size()-1).getFigura());
                }
                
                //Cria novos blocos enquanto não chega na borda
                while(blocos.get(blocos.size()-1).getFigura().getX() + 2*(blocos.get(0).getFigura().getWidth() + m) <= this.getWidth()){

                    //if para inserir bloco muro na 4ª coluna
                    if(col == 3 && NovoNivel.getNivel() == 2){
                        image = new Image(new FileInputStream("src\\Principal\\img\\muroTrancado.png"));
                        criaMuro();
                    } else{
                        indGol = (int) (Math.random()*imgGols.length);
                        defineBloco();
                        image = new Image(new FileInputStream("src\\Principal\\img\\gols\\"+imgGols[indGol].getName()));
                    }
                    image_pattern = new ImagePattern(image);
                    blocos.get(blocos.size()-1).getFigura().setFill(image_pattern); 
                    getChildren().add(blocos.get(blocos.size()-1).getFigura());
                    
                    col++;
                }
            }
        } catch (FileNotFoundException ex){
            Logger.getLogger(ArkanoidGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Define a est com base no nivel escolhido no menu
    private int defineVelocidade() {
        if(NovoNivel.getNivel() == 1)
            return 2;
        if(NovoNivel.getNivel() == 2)
            return 3;
        return 0;
    }

    //Adiciona uma nova bolinha no array de bolinhas
    private void adicionaBolinha() {
        b.add(new Bolinha((int) (this.getWidth()/2),400,15,defineVelocidade(),this));
        try {
            Image image = new Image(new FileInputStream("src\\Principal\\img\\bola.png"));
            ImagePattern image_pattern = new ImagePattern(image);
            b.get(b.size()-1).getFigura().setFill(image_pattern);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArkanoidGame.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        getChildren().add(b.get(b.size()-1).getFigura());
    }

    //Define qual o tipo do bloco deve ser criado
    private void defineBloco(){
        if(imgGols[indGol].getName().endsWith("Amarelo.png"))
            bAmarelo();    
        else if(imgGols[indGol].getName().endsWith("Verde.png"))
            bVerde();
        else if(imgGols[indGol].getName().endsWith("Azul.png"))
           bAzul();
        else if(imgGols[indGol].getName().endsWith("Branco.png"))
            bBranco();
        else
            bVermelho();
    }
    
    private void criaMuro() {
        blocos.add(new BlocoMuro((int) (blocos.get(blocos.size()-1).getFigura().getX()+ blocos.get(0).getFigura().getWidth() + m), 
        (int) (blocos.get(blocos.size()-1).getFigura().getY()), 75, 50, this, 10));
    }
    
    //Adiciona um novo bloco no array da cor amarela
    private void bAmarelo() {
        blocos.add(new BlocoAmarelo ((int) (blocos.get(blocos.size()-1).getFigura().getX()+ blocos.get(0).getFigura().getWidth() + m), 
        (int) (blocos.get(blocos.size()-1).getFigura().getY()), 75, 50, this, 10));
    }
    //Adiciona um novo bloco no array da cor amarela
    private void bVermelho() {
        blocos.add(new BlocoVermelho ((int) (blocos.get(blocos.size()-1).getFigura().getX()+ blocos.get(0).getFigura().getWidth() + m), 
        (int) (blocos.get(blocos.size()-1).getFigura().getY()), 75, 50, this, 10));
    }
    //Adiciona um novo bloco no array da cor verde
    private void bVerde() {
        blocos.add(new BlocoVerde((int) (blocos.get(blocos.size()-1).getFigura().getX()+ blocos.get(0).getFigura().getWidth() + m), 
        (int) (blocos.get(blocos.size()-1).getFigura().getY()), 75, 50, this, 10,0));
    }

    //Adiciona um novo bloco no array da cor azul
    private void bAzul() {
         blocos.add(new BlocoAzul((int) (blocos.get(blocos.size()-1).getFigura().getX()+ blocos.get(0).getFigura().getWidth() + m), 
        (int) (blocos.get(blocos.size()-1).getFigura().getY()), 75, 50, this, 10));
    }

    //Adiciona um novo bloco no array da cor branco
    private void bBranco() {
        blocos.add(new BlocoBranco((int) (blocos.get(blocos.size()-1).getFigura().getX()+ blocos.get(0).getFigura().getWidth() + m), 
        (int) (blocos.get(blocos.size()-1).getFigura().getY()), 75, 50, this, 10));
    }

}
