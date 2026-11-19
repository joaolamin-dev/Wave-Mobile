package br.com.fatec.wave.mobile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.fatec.wave.mobile.data.Artista
import br.com.fatec.wave.mobile.data.Musica
import br.com.fatec.wave.mobile.data.Repositorio

class CatalogoViewModel : OperacaoViewModel() {
    var musicas by mutableStateOf<List<Musica>>(emptyList())
        private set
    var artistas by mutableStateOf<List<Artista>>(emptyList())
        private set
    var musicaAtual by mutableStateOf<Musica?>(null)
        private set
    var artistaAtual by mutableStateOf<Artista?>(null)
        private set

    fun listarMusicas() = executar {
        musicas = Repositorio.listarMusicas().content ?: emptyList()
    }

    // Texto vazio lista tudo
    fun buscarMusicas(titulo: String) {
        if (titulo.isBlank()) {
            listarMusicas()
        } else {
            executar { musicas = Repositorio.buscarMusicas(titulo.trim()).content ?: emptyList() }
        }
    }

    fun abrirMusica(id: Long) {
        musicaAtual = null
        executar { musicaAtual = Repositorio.musica(id) }
    }

    fun listarArtistas() = executar {
        artistas = Repositorio.listarArtistas().content ?: emptyList()
    }

    fun buscarArtistas(nome: String) {
        if (nome.isBlank()) {
            listarArtistas()
        } else {
            executar { artistas = Repositorio.buscarArtistas(nome.trim()).content ?: emptyList() }
        }
    }

    fun abrirArtista(id: Long) {
        artistaAtual = null
        executar { artistaAtual = Repositorio.artista(id) }
    }

    fun limpar() {
        cancelar()
        musicas = emptyList()
        artistas = emptyList()
        musicaAtual = null
        artistaAtual = null
        limparMensagem()
    }
}
