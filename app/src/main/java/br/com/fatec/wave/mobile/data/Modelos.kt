package br.com.fatec.wave.mobile.data

// Corpos enviados para a API
data class LoginEnvio(val email: String, val senha: String)

data class CadastroEnvio(val nome: String, val email: String, val senha: String)

// Respostas da API (campos anuláveis, pois vêm do Gson)
data class LoginResposta(val token: String?)

data class Usuario(val id: Long?, val nome: String?, val email: String?)

data class Perfil(val id: Long?, val nome: String?, val email: String?, val role: String?)

data class Musica(
    val id: Long?,
    val titulo: String?,
    val artistaNome: String?,
    val genero: String?,
    val anoLancamento: Int?
)

data class Artista(val id: Long?, val nome: String?, val nacionalidade: String?, val tipo: String?)

data class Playlist(val id: Long?, val nome: String?, val musicas: List<Musica>?)

// Página do Spring: só o conteúdo interessa
data class Pagina<T>(val content: List<T>?)
