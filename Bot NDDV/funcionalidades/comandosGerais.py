import asyncio

import discord

class comandosGerais():

    def __init__(self) -> None:
        pass

    # /announce [roles,separados,por,virgula] mensagem a enviar
    def comandoNotify(message, guild):
        try:
            fimRoles = message.content.find(']')

            listaRoles = message.content.split(" ")[1].split(",") # Extrair da string os roles
            listaRoles[0] = listaRoles[0].replace('[', "")
            listaRoles[len(listaRoles)-1] = listaRoles[len(listaRoles)-1].replace(']', "")
            
            anuncio = message.content[fimRoles+1:]
            
            out = ""
            for x in listaRoles:
                out = "{0}{1} ".format(out, discord.utils.get(guild.roles, name = x).mention)
            out = out + anuncio
        except:
            out = "O comando não foi bem introduzido. O formato é:\n/announce [roles,separados,por,virgula] mensagem a enviar "
        return out

    async def comandoJogoDoMes(self, message, client):
        await message.channel.send("Quer saber a data escreva 'data', o nome do jogo escreva 'nome' e ambas escreva 'informação'.")

        def verificar(m):
            return m.channel == message.channel and m.author == message.author
        
        try:
            out = await client.wait_for('message', timeout = 30.0, check = verificar) # Estamos a espera que o user mande uma mensagem que satisfaça a func verificar(m)
            
            if out.content.upper() == 'DATA':
                out = self.returnData(self)
            elif out.content.upper() == 'NOME':
                out = self.returnTitulo(self)
            elif out.content.upper() == 'INFORMAÇÃO':
                print("cheguei aqui")
                out = self.returnTodaInformacao(self)
                print("cheguei aqui")
            else:
                out = "Opção inválida."

        except asyncio.TimeoutError: # Caso passsem 30.0 segundos, a função da raise a uma exception
            out = "Passou o tempo válido para introduzir uma opção."

        return out

    # /updateJM "Titulo do jogo" "data do evento"
    def comandoUpdateJogoDoMes(self, message):
        indicesAspas = [i for i in range (len(message.content)) if  message.content[i] == '"']
        tituloJogo = message.content[ indicesAspas[0]+1 : indicesAspas[1]]
        dataEvento = message.content[ indicesAspas[2]+1 : indicesAspas[3]]
        out = self.updateJogo(self, tituloJogo, dataEvento)
        return out

    