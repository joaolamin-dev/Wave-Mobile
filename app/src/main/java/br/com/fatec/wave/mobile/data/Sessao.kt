package br.com.fatec.wave.mobile.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// Token guardado só em memória
object Sessao {
    var token by mutableStateOf<String?>(null)
}
