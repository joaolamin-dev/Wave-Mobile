package br.com.fatec.wave.mobile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.fatec.wave.mobile.data.Repositorio
import br.com.fatec.wave.mobile.data.Sessao

class AuthViewModel : OperacaoViewModel() {
    // Sinais para a navegação
    var entrou by mutableStateOf(false)
        private set
    var cadastrou by mutableStateOf(false)
        private set

    fun cadastrar(nome: String, email: String, senha: String) {
        if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
            mensagem = "Preencha todos os campos."
            return
        }
        if (senha.length < 6) {
            mensagem = "A senha deve ter pelo menos 6 caracteres."
            return
        }
        executar {
            Repositorio.cadastrar(nome.trim(), email.trim(), senha)
            mensagem = "Conta criada! Faça login."
            cadastrou = true
        }
    }

    fun login(email: String, senha: String) {
        if (email.isBlank() || senha.isBlank()) {
            mensagem = "Preencha e-mail e senha."
            return
        }
        executar {
            val resposta = Repositorio.login(email.trim(), senha)
            Sessao.token = resposta.token ?: error("Token não recebido")
            entrou = true
        }
    }

    // Chamado depois que a tela já navegou
    fun consumirEntrada() {
        entrou = false
        cadastrou = false
    }

    fun sair() {
        cancelar()
        Sessao.token = null
        consumirEntrada()
        limparMensagem()
    }
}
