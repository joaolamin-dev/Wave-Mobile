package br.com.fatec.wave.mobile.data

import com.google.gson.JsonParser
import retrofit2.Response

object Repositorio {
    private val api = ApiClient.api

    suspend fun login(email: String, senha: String) = ler { api.login(LoginEnvio(email, senha)) }

    suspend fun cadastrar(nome: String, email: String, senha: String) =
        ler { api.cadastrar(CadastroEnvio(nome, email, senha)) }

    suspend fun meuPerfil() = ler { api.meuPerfil() }

    suspend fun minhasPlaylists() = ler { api.minhasPlaylists() }

    suspend fun listarMusicas() = ler { api.listarMusicas() }

    suspend fun buscarMusicas(titulo: String) = ler { api.buscarMusicas(titulo) }

    suspend fun musica(id: Long) = ler { api.musica(id) }

    suspend fun listarArtistas() = ler { api.listarArtistas() }

    suspend fun buscarArtistas(nome: String) = ler { api.buscarArtistas(nome) }

    suspend fun artista(id: Long) = ler { api.artista(id) }

    // Executa a chamada e trata erros da API
    private suspend fun <T> ler(chamada: suspend () -> Response<T>): T {
        val tinhaToken = Sessao.token != null
        val resposta = chamada()
        if (!resposta.isSuccessful) {
            val mensagem = try {
                JsonParser.parseString(resposta.errorBody()?.string())
                    .asJsonObject.get("message").asString
            } catch (e: Exception) {
                "Erro HTTP ${resposta.code()}"
            }
            if (tinhaToken && resposta.code() == 401) {
                Sessao.token = null
                error("Sessão encerrada. Faça login novamente.")
            }
            error(mensagem)
        }
        return resposta.body() ?: error("Resposta vazia da API")
    }
}
