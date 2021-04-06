
def modulosImportados():
    from .comandosGerais import comandosGerais
    from .jogoDoMes import jogoDoMes

    modulos = comandosGerais, jogoDoMes 
    return modulos

def importJogoDoMes():
    from .jogoDoMes import jogoDoMes
    return jogoDoMes

def importComandosGerais():
    from .comandosGerais import comandosGerais
    return comandosGerais


