import discord

from funcionalidades import modulosImportados

client = discord.Client()
comandos, jogoM = modulosImportados()

# Canais e server
guild = None
botCommandsChannel = None
anunciosChannel = None

@client.event
async def on_ready(): # Função que é ativada quando o bot se liga após ter estado desligado
    print("Sou o bot e estou online. Skynet here we go!")
    global guild, botCommandsChannel, anunciosChannel
    guild = client.get_guild(683101641217409038) # id do server
    botCommandsChannel = guild.get_channel(828740580463869994) # channel de comandos do bot
    anunciosChannel = guild.get_channel(649397144759042081)

@client.event
async def on_message(message): # Comandos que um user pode invocar
    conteudoMensagem = message.content.upper()
    if message.author == client.user:
        return # A mensagem veio do bot
    
    if conteudoMensagem.startswith("/NOTIFY"): # Notificar pessoas com um dado role de alguma coisa (mensagem)
        if message.channel == botCommandsChannel:
            out = comandos.comandoNotify(message, guild)

            await anunciosChannel.send(out)
        else:
            await message.channel.send("Este comando não pode ser invocado aqui.")
    
    elif conteudoMensagem == "/JOGODOMES": # Saber informações sobre o jogo do mês
        out = await comandos.comandoJogoDoMes(jogoM, message, client)
        await message.channel.send(out)

    elif conteudoMensagem.startswith("/UPDATEJM"): # Atualizar informações sobre o jogo do mês
        if message.channel == botCommandsChannel:
            out = comandos.comandoUpdateJogoDoMes(jogoM, message)
            await message.channel.send(out)
        else:
            await message.channel.send("Este comando não pode ser invocado aqui.")

if __name__ == '__main__':
    client.run("ODAyODgxOTUwNDY1NTIzNzEz.YA1sFQ.kFiI9WHonRJ8o1uZ941HPuYSMLU")