package br.com.fatec.wave.mobile.ui

import kotlinx.serialization.Serializable

// Rotas tipadas da navegação
@Serializable
data object Login

@Serializable
data object Cadastro

@Serializable
data object Inicio

@Serializable
data object Musicas

@Serializable
data object Artistas

@Serializable
data object Perfil

@Serializable
data class MusicaDetalhe(val id: Long)

@Serializable
data class ArtistaDetalhe(val id: Long)
