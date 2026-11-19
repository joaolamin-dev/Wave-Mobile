package br.com.fatec.wave.mobile.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @POST("login")
    suspend fun login(@Body corpo: LoginEnvio): Response<LoginResposta>

    @POST("usuarios")
    suspend fun cadastrar(@Body corpo: CadastroEnvio): Response<Usuario>

    @GET("meu-perfil")
    suspend fun meuPerfil(): Response<Perfil>

    @GET("meu-perfil/playlists")
    suspend fun minhasPlaylists(): Response<List<Playlist>>

    @GET("musicas")
    suspend fun listarMusicas(@Query("size") size: Int = 50): Response<Pagina<Musica>>

    @GET("musicas/buscar-titulo")
    suspend fun buscarMusicas(
        @Query("titulo") titulo: String,
        @Query("size") size: Int = 50
    ): Response<Pagina<Musica>>

    @GET("musicas/{id}")
    suspend fun musica(@Path("id") id: Long): Response<Musica>

    @GET("artistas")
    suspend fun listarArtistas(@Query("size") size: Int = 50): Response<Pagina<Artista>>

    @GET("artistas/buscar-nome")
    suspend fun buscarArtistas(
        @Query("nome") nome: String,
        @Query("size") size: Int = 50
    ): Response<Pagina<Artista>>

    @GET("artistas/{id}")
    suspend fun artista(@Path("id") id: Long): Response<Artista>
}
