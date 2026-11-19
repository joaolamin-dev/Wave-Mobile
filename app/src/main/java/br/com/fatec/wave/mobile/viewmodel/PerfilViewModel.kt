package br.com.fatec.wave.mobile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.fatec.wave.mobile.data.Perfil
import br.com.fatec.wave.mobile.data.Playlist
import br.com.fatec.wave.mobile.data.Repositorio

class PerfilViewModel : OperacaoViewModel() {
    var perfil by mutableStateOf<Perfil?>(null)
        private set
    var playlists by mutableStateOf<List<Playlist>>(emptyList())
        private set

    fun carregar() = executar {
        perfil = Repositorio.meuPerfil()
        playlists = Repositorio.minhasPlaylists()
    }

    fun limpar() {
        cancelar()
        perfil = null
        playlists = emptyList()
        limparMensagem()
    }
}
