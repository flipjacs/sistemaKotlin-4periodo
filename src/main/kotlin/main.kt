class Midia(val titulo: String, val ano: Int, val tipo: String, val genero: String) {
    val episodios: MutableList<Episodio> = mutableListOf()

    fun adicionarEpisodio(episodio: Episodio) {
        episodios.add(episodio)
    }

    fun mostrarDados() {
        println("$titulo - Ano: $ano - Tipo: $tipo")
        mostrarGenero(genero)
    }
}

class Episodio(val numero: Int, val duracaoMinutos: Int, val comentario: String?) {
    fun mostrarComentario() {
        comentario?.let {
            println("Comentário: $it")
        } ?: println("Sem comentários")
    }
}

class Usuario(val nome: String) {
    val episodiosAssistidosHoje: MutableList<Episodio> = mutableListOf()
    val avaliacoes: MutableMap<String, Int> = mutableMapOf()

    fun assistirEpisodio(episodio: Episodio) {
        if (episodio in episodiosAssistidosHoje) {
            println("Este episódio já foi marcado como assistido hoje.")
        } else {
            episodiosAssistidosHoje.add(episodio)
            println("Episódio marcado como assistido hoje.")
        }
    }

    fun avaliarMidia(midia: Midia, nota: Int) {
        avaliacoes[midia.titulo] = nota
    }
}

fun mostrarGenero(genero: String) {
    when (genero.lowercase()) {
        "acao" -> println("Gênero: Ação")
        "comedia" -> println("Gênero: Comédia")
        "drama" -> println("Gênero: Drama")
        "ficcao" -> println("Gênero: Ficção")
        else -> println("Gênero não cadastrado")
    }
}

fun escolherMidia(midias: List<Midia>): Midia? {
    for (indice in midias.indices) {
        println("${indice + 1} - ${midias[indice].titulo}")
    }
    print("Escolha uma mídia: ")
    val numero = readLine()?.toIntOrNull()

    if (numero != null && numero in 1..midias.size) {
        return midias[numero - 1]
    } else {
        println("Mídia inválida.")
        return null
    }
}

fun escolherEpisodio(midia: Midia): Episodio? {
    if (midia.episodios.isEmpty()) {
        println("Esta mídia não possui episódios.")
        return null
    }

    println("Episódios da 1ª temporada de ${midia.titulo}:")
    for (episodio in midia.episodios) {
        println("${episodio.numero} - ${episodio.duracaoMinutos} minutos")
    }
    print("Escolha um episódio: ")
    val numero = readLine()?.toIntOrNull()

    for (episodio in midia.episodios) {
        if (episodio.numero == numero) {
            return episodio
        }
    }
    println("Episódio inválido.")
    return null
}

fun calcularTempoAssistido(usuario: Usuario) {
    var tempoTotal = 0
    for (episodio in usuario.episodiosAssistidosHoje) {
        tempoTotal += episodio.duracaoMinutos
    }

    val horasAssistidas: Double = tempoTotal / 60.0
    println("Tempo total assistido hoje: $tempoTotal minutos")
    println("Em horas: %.2f".format(horasAssistidas))

    if (tempoTotal > 180) {
        println("Você está maratonando! Lembre-se de beber água e esticar as pernas")
    } else {
        println("Tempo de maratona dentro do limite.")
    }
}

fun main() {
    val breakingBad = Midia("Breaking Bad", 2008, "Série", "drama")
    breakingBad.adicionarEpisodio(Episodio(1, 47, "Começo muito bom"))
    breakingBad.adicionarEpisodio(Episodio(2, 48, null))
    breakingBad.adicionarEpisodio(Episodio(3, 45, "Gostei bastante"))
    breakingBad.adicionarEpisodio(Episodio(4, 50, null))


    val interestelar = Midia("Interestelar", 2014, "Filme", "ficcao")

    val theOffice = Midia("The Office", 2005, "Série", "comedia")
    theOffice.adicionarEpisodio(Episodio(1, 23, "Engraçado"))
    theOffice.adicionarEpisodio(Episodio(2, 22, null))

    val midias: List<Midia> = listOf(breakingBad, interestelar, theOffice)
    val usuario = Usuario("Felipe")
    var opcao: Int?

    do {
        println("\n===== GUIA DE SÉRIES E FILMES =====")
        println("Usuário: ${usuario.nome}")
        println("1 - Listar mídias")
        println("2 - Ver episódios")
        println("3 - Marcar episódio como assistido hoje")
        println("4 - Calcular tempo assistido hoje")
        println("5 - Avaliar mídia")
        println("6 - Ver comentário de episódio")
        println("0 - Sair")
        print("Escolha uma opção: ")
        val entrada = readLine() ?: break
        opcao = entrada.toIntOrNull()

        when (opcao) {
            1 -> {
                for (midia in midias) {
                    midia.mostrarDados()
                }
            }
            2 -> {
                val midia = escolherMidia(midias)
                if (midia != null) {
                    if (midia.episodios.isEmpty()) {
                        println("Esta mídia não possui episódios.")
                    } else {
                        println("Episódios da 1ª temporada de ${midia.titulo}:")
                        for (episodio in midia.episodios) {
                            println("${episodio.numero} - ${episodio.duracaoMinutos} minutos")
                        }
                    }
                }
            }
            3 -> {
                val midia = escolherMidia(midias)
                if (midia != null) {
                    val episodio = escolherEpisodio(midia)
                    if (episodio != null) usuario.assistirEpisodio(episodio)
                }
            }
            4 -> calcularTempoAssistido(usuario)
            5 -> {
                val midia = escolherMidia(midias)
                if (midia != null) {
                    print("Dê uma nota de 1 a 5: ")
                    val nota = readLine()?.toIntOrNull()
                    val classificacao = when (nota) {
                        1 -> "Péssimo"
                        2 -> "Ruim"
                        3 -> "Bom"
                        4 -> "Muito bom"
                        5 -> "Obra-prima"
                        else -> "Nota inválida"
                    }
                    println(classificacao)
                    if (nota != null && nota in 1..5) {
                        usuario.avaliarMidia(midia, nota)
                    }
                }
            }
            6 -> {
                val midia = escolherMidia(midias)
                if (midia != null) {
                    val episodio = escolherEpisodio(midia)
                    episodio?.mostrarComentario()
                }
            }
            0 -> println("Até logo!")
            else -> println("Opção inválida.")
        }
    } while (opcao != 0)
}
