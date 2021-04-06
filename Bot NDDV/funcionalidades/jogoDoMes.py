import discord

class jogoDoMes:
    tituloDoJogo = None
    dataDoJogo = None

    def __init__(self):
        pass

    def getTitulo(self):
        return self.tituloDoJogo

    def getData(self):
        return self.dataDoJogo
        
    def clearJogo(self): # Quando o jogo do mês já foi e ainda não foi decidido o seguinte
        self.tituloDoJogo = None
        self.dataDoJogo = None

    def updateJogo(self, nomeJogo = None, dataEvento = None): # *args significa que podemos ter um numero variável de argumentos
        if nomeJogo is None and dataEvento is None:
            return "Não foram introduzidas informações suficientes."
        if nomeJogo is not None:
            self.tituloDoJogo = nomeJogo
        if dataEvento is not None:
            self.dataDoJogo = dataEvento
        return "Informações atualizadas com sucesso."


    def returnTitulo(self):
        if self.tituloDoJogo is not None: # O titulo do jogo já foi decidido
            out = "Este mês vamos falar sobre \"{0}\".".format( self.getTitulo(self) )
        else:
            out = "Ainda não foi decidido qual vai ser o jogo que vamos falar este mês."
        return out

    def returnData(self):
        if self.dataDoJogo is not None: # Já sabemos qual é a data
            out = "O jogo do mês está marcado para {0}. Esperamos ver-te lá!".format( self.getData(self) )
        else:
            out = "Ainda não foi marcada a data do jogo do mês."
        return out

    def returnTodaInformacao(self):
        print(type(self.tituloDoJogo),type(self.tituloDoJogo))
        if self.tituloDoJogo is not None: # O titulo do jogo já foi decidido
            if self.dataDoJogo is not None: # Já sabemos qual é a data
                out = "Este mês vamos falar sobre \"{0}\" no dia {1}. Se fosse a ti ia mas como não mando em ti faz o que quiseres.".format(self.getTitulo(self), self.getData(self))
            else:
                out = "Este mês vamos falar sobre \"{0}\" mas ainda não sabemos o dia. Fica atento ao servidor para saber a data.".format(self.getTitulo(self))

        else: # Não se sabe qual é o jogo
            if self.dataDoJogo is not None: # Não se sabe o título mas sabe-se a data
                out = "Este mês ainda não foi decidido qual vai ser o titulo mas vai ser no dia {0}. Fica atento ao servidor para saber qual o jogo que vamos discutir.".format(self.getTitulo(self))
            else:
                out = "Estamos no processo de decisão sobre o jogo do mês. Fica atento ao servidor para saber mais informações."
        return out