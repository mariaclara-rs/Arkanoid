# Arkanoid
Jogo clássico Arkanoid em um versão com o tema futebol.

Existem dois modos de jogo: normal e difícil.

Componentes do jogo
  • Bolinha: há um número finito de bolinhas, elas se movem na vertical (sobe e desce) e nas diagonais, é o objeto principal do jogo, tem a missão de destruir os blocos, quando as bilinhas se esgotam, o jogo é finalizado;
  • Raquete: tem o objetivo de rebater as bolinhas, move para direita e esquerda, é controlada pelo mouse;
  • Blocos (gols) : tem comportamento distinto e cores diferentes
    • Vermelho: quando a bolinha colide com este bloco ele é destruído e a bolinha fica invisível;
    • Branco: quando a bolinha colide com este bloco ele é destruído e a bolinha ganha o dobro de velocidade por 15 segundos;
    • Verde: na 1ª com a bolinha, desce próximo a raquete e fica se movimentando na horizontal. Na 2ª colisão, some;
    • Azul: quando acontece a colisão ele é destruído e uma segundo bolinha é gerada com direção horizontal oposta à bolinha atual;
    • Amerelo: permanece estático, é destruído com uma única colisão;
    • Cadeado fechado -> Cadeado aberto: são necessárias duas colisões para destruir este bloco. Quando isso acontece a raquere aumenta seu tamanho. Este bloco só esta presente no modo de jogo difícil.

Cada bloco são 10 pontos para o jogafor;
O 'P' pausa o jogo.

  
