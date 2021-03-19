import pygame
pygame.font.init()
pygame.init()

"""
    No pygame o eixo xOy está centrado no campo superior esquerdo da tela,
    ou seja só podemos ter coisas no 1º quadrante (apesar de visualmente
    parecer que estamos a trabalhar no 4º quadrante). Quando pomos uma peça,
    seguindo as regras do jogo original, quando carregamos numa coluna, a 
    peça "cai" para a linha mais abaixo que não está ocupada por uma outra
    peça. Se usarmos como exemplo a primeira peça, visualmente, estamos
    a por a peça na linha 0, quando na verdade, devido à forma de como verifico
    onde é que a pessoa carregou, em vez de na var campo aparecer na linha 0, 
    aparece na linha 5. Por isso, para inserir na var campo na posição correta
    fazemos campo[ 5 - linha][coluna] = jogador
"""

# Nome da window
pygame.display.set_caption("4 Em Linha")

# Constantes
COMPRIMENTO, ALTURA = 950, 825
NUMERO_LINHAS, NUMERO_COLUNAS = 5, 6 # Começando a contar do 0
FPS = 60
RAIO_CIRCULO = 50
LETRA_VENCEDOR = pygame.font.SysFont('comicsans', 75)

# Path Imagens
PATH_FUNDO = "C:\\Users\\gonca\\Documents\\Python\\Programas\\4 em linha\\background.jpg"

# Cores no formato RGB
CINZENTO = (128,128,128)
BORDO = (128,0,0)
OURO = (255,215,0)
BRANCO = (255,255,255)

# Janela onde o jogo decorre
JANELA = pygame.display.set_mode((COMPRIMENTO, ALTURA))
FUNDO = pygame.transform.scale( pygame.image.load(PATH_FUNDO) , (COMPRIMENTO, ALTURA) )

# Lista com as posições das (possíveis) peças
boundingBoxPecas = [] # lista tuplos que representam o valor x do canto superior esquerdo e valor y do inferior direito da bounding box

# Coordenadas do centro das peças onde se pode jogar a qualquer momento
pecasDisponiveis = []

""" 
    Matriz que representa o estado do jogo: 
        -1 -> aquele lugar não tem lá nenhuma peça
        1 -> aquele lugar tem uma peça do jogador 1
        2 -> aquele lugar tem uma peça do jogador 2
"""
campo = []

""" 
    Indice y das peças que se pode jogar. Usar em conjunto com a var 'pecasDisponiveis', sendo que esta
    da a posicao x das mesmas no campo
"""
indiceYPecasDisponiveis = []

def initVars():
    tmpCampo = []
    for i in range (0, 6):
        linha = []
        for j in range (0, 7):
            linha.append(-1)
        tmpCampo.append(linha)
    global campo 
    campo = tmpCampo
    global indiceYPecasDisponiveis
    indiceYPecasDisponiveis = []
    for i in range (0, 7):
        indiceYPecasDisponiveis.append(0)
    
    del pecasDisponiveis[:]

def inicioDoJogo():
    JANELA.blit(FUNDO, (0,0))

    linha = 100
    coluna = 100
    while linha <= 725:# coordenada y do centro
        while coluna <= 850:# coordenada x do centro
            pygame.draw.circle(JANELA , CINZENTO, (coluna, linha), RAIO_CIRCULO)
            boundingBoxPecas.append( (coluna-RAIO_CIRCULO, linha+RAIO_CIRCULO) )
            if linha == 725:
                pecasDisponiveis.append( (coluna, linha) )
            coluna += 125
        linha += 125
        coluna = 100

    pygame.display.update()

def jogada(ratoPosX, jogador):
    for i in range (0, len(pecasDisponiveis)):
        x = pecasDisponiveis[i]
        if (x[0] - RAIO_CIRCULO < ratoPosX < x[0] + RAIO_CIRCULO): # se a posicao 'x' do rato esta contido em alguma das bounding boxes das peças
            if x[1] >= 100: # Verifica se ainda se pode inserir a peça na coluna onde o jogador clicou
                indiceColunaPecaJogada = i
                indiceLinhaPecaJogada = indiceYPecasDisponiveis[i]
                if jogador == 1: # foi o jogador 1 que fez esta jogada
                    pygame.draw.circle(JANELA , BORDO, (x[0], x[1]), RAIO_CIRCULO)
                else: # foi o jogador 2 que fez esta jogada
                    pygame.draw.circle(JANELA , OURO, (x[0], x[1]), RAIO_CIRCULO)
                pecasDisponiveis[i] = (x[0], x[1]-125)
                if indiceYPecasDisponiveis[i] != 5:
                    indiceYPecasDisponiveis[i] += 1
            else: # O rato esta numa posicao x valida, mas não se pode jogar peças naquela coluna
                break 
            pygame.display.update()
            return 0, indiceColunaPecaJogada, indiceLinhaPecaJogada # A jogada é considerada válida e por isso foi efetuada
    return 1, -1, -1 # A jogada não é considerada válida e por isso não foi efetuada

def verificarJogadorVenceu(colunaPeca, linhaPeca, jogador):
    # Verificar se o jogador fez uma sequência de 4 peças na vertical
    if linhaPeca + 3 <= NUMERO_LINHAS: # A ultima peça colocada está acima das outras todas
        if campo[linhaPeca+1][colunaPeca] == campo[linhaPeca+2][colunaPeca] == campo[linhaPeca+3][colunaPeca] == jogador:
            return 0

    # Verificar se o jogador fez uma sequência de 4 peças na horizontal
    if colunaPeca - 3 >= 0: # A ultima peça colocada está à direita das outras todas
        if campo[linhaPeca][colunaPeca-1] == campo[linhaPeca][colunaPeca-2] == campo[linhaPeca][colunaPeca-3] == jogador:
            return 0
    if colunaPeca + 3 <= NUMERO_COLUNAS: # A ultima peça colocada está à esquerda das outras todas
        if campo[linhaPeca][colunaPeca+1] == campo[linhaPeca][colunaPeca+2] == campo[linhaPeca][colunaPeca+3] == jogador:
            return 0
    if colunaPeca - 1 >= 0 and colunaPeca + 2 <= NUMERO_COLUNAS: # Tem uma peça à esquerda e duas à direita
        if campo[linhaPeca][colunaPeca-1] == campo[linhaPeca][colunaPeca+1] == campo[linhaPeca][colunaPeca+2] == jogador:
            return 0
    if colunaPeca - 2 >= 0 and colunaPeca + 1 <= NUMERO_COLUNAS: # Tem duas peça à esquerda e uma à esquerda
        if campo[linhaPeca][colunaPeca-1] == campo[linhaPeca][colunaPeca-2] == campo[linhaPeca][colunaPeca+1] == jogador:
            return 0

    # Verificar se o jogador fez uma sequência de 4 peças numa diagonal descendente
    """
        *
         *
          *
           *
    """
    if linhaPeca + 3 <= NUMERO_LINHAS and colunaPeca + 3 <= NUMERO_COLUNAS: # A ultima peça foi colocada acima das outras todas
        if campo[linhaPeca+1][colunaPeca+1] == campo[linhaPeca+2][colunaPeca+2] == campo[linhaPeca+3][colunaPeca+3] == jogador:
            return 0
    if linhaPeca - 3 >= 0 and colunaPeca - 3 >= 0: # A ultima peça foi colocada por baixo das outras todas
        if campo[linhaPeca-1][colunaPeca-1] == campo[linhaPeca-2][colunaPeca-2] == campo[linhaPeca-3][colunaPeca-3] == jogador:
            return 0
    if (linhaPeca - 1 >= 0 and colunaPeca - 1 >= 0) and (linhaPeca + 2 <= NUMERO_LINHAS and colunaPeca + 2 <= NUMERO_COLUNAS): # A ultima peça foi colocada com uma peça à sua esquerda e duas à sua direita
        if campo[linhaPeca-1][colunaPeca-1] == campo[linhaPeca+1][colunaPeca+1] == campo[linhaPeca+2][colunaPeca+2] == jogador:
            return 0
    if (linhaPeca - 2 >= 0 and colunaPeca - 2 >= 0) and (linhaPeca + 1 <= NUMERO_LINHAS and colunaPeca + 1 <= NUMERO_COLUNAS): # A ultima peça foi colocada com duas peças à sua esquerda e uma à sua direita
        if campo[linhaPeca-1][colunaPeca-1] == campo[linhaPeca-2][colunaPeca-2] == campo[linhaPeca+1][colunaPeca+1] == jogador:
            return 0
    
    # Verificar se o jogador fez uma sequência de 4 peças numa diagonal ascendente
    """
           *
          *
         *
        *
    """
    if linhaPeca + 3 <= NUMERO_LINHAS and colunaPeca - 3 >= 0: # A ultima peça foi colocada acima das outras todas
        if campo[linhaPeca+1][colunaPeca-1] == campo[linhaPeca+2][colunaPeca-2] == campo[linhaPeca+3][colunaPeca-3] == jogador:
            return 0
    if linhaPeca - 3 >= 0 and colunaPeca + 3 <= NUMERO_COLUNAS: # A ultima peça foi colocada por baixo das outras todas
        if campo[linhaPeca-1][colunaPeca+1] == campo[linhaPeca-2][colunaPeca+2] == campo[linhaPeca-3][colunaPeca+3] == jogador:
            return 0
    if (linhaPeca + 1 <= NUMERO_LINHAS and colunaPeca - 1 >= 0) and (linhaPeca - 2 >= 0 and colunaPeca + 2 <= NUMERO_COLUNAS): # A ultima peça foi colocada com uma peça à sua esquerda e duas à sua direita
        if campo[linhaPeca+1][colunaPeca-1] == campo[linhaPeca-1][colunaPeca+1] == campo[linhaPeca-2][colunaPeca+2] == jogador:
            return 0
    if (linhaPeca + 2 <= NUMERO_LINHAS and colunaPeca - 2 >= 0) and (linhaPeca - 1 >= 0 and colunaPeca + 1 <= NUMERO_COLUNAS): # A ultima peça foi colocada com duas peças à sua esquerda e uma à sua direita
        if campo[linhaPeca+1][colunaPeca-1] == campo[linhaPeca+2][colunaPeca-2] == campo[linhaPeca-1][colunaPeca+1] == jogador:
            return 0
    
    return -1        

def mostrarVencedor(texto):
    texto_vencedor = LETRA_VENCEDOR.render(texto, 1, BRANCO)
    JANELA.blit(texto_vencedor, ( COMPRIMENTO//2 - texto_vencedor.get_width()//2,  ALTURA//2 - texto_vencedor.get_height()//2 ))
    pygame.display.update()
    pygame.time.delay(5000)

def main():
    initVars()
    inicioDoJogo()
    jogador = 1
    while True:
        venceu = -1
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
            if event.type == pygame.MOUSEBUTTONUP:
                posRato = pygame.mouse.get_pos()
                sucesso, coluna, linha = jogada(posRato[0], jogador)
                if sucesso == 0: # ( 0 se sucesso -1 se nao , indice se sucesso -1 se insucesso )
                    campo[5 - linha][coluna] = jogador # Ver comentário inicio do programa
                    venceu = verificarJogadorVenceu(coluna, 5 - linha, jogador)
                    if jogador == 1:
                        jogador = 2
                    else:
                        jogador = 1
        if venceu == 0:
            texto = ""
            if jogador == 1:
                texto = "Parabéns ao jogador 1, venceu."
            else:
                texto = "Parabéns ao jogador 1, venceu."
            mostrarVencedor(texto)
            break           
    main()

if __name__ == "__main__":
    main()