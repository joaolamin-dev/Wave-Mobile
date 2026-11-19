package br.com.fatec.wave.mobile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

// Base com estado de carregamento e mensagem de erro/aviso
open class OperacaoViewModel : ViewModel() {
    var ocupado by mutableStateOf(false)
        private set
    var mensagem by mutableStateOf<String?>(null)
        protected set

    private var tarefa: Job? = null

    protected fun executar(acao: suspend () -> Unit) {
        if (ocupado) return
        ocupado = true
        mensagem = null
        tarefa = viewModelScope.launch {
            try {
                acao()
            } catch (e: CancellationException) {
                throw e
            } catch (e: IOException) {
                mensagem = "Falha de conexão. O servidor pode estar iniciando (até 1 minuto). Tente novamente."
            } catch (e: Exception) {
                mensagem = e.message
            } finally {
                ocupado = false
            }
        }
    }

    fun limparMensagem() {
        mensagem = null
    }

    fun cancelar() {
        // O finally de executar volta ocupado para false
        tarefa?.cancel()
        tarefa = null
    }
}
