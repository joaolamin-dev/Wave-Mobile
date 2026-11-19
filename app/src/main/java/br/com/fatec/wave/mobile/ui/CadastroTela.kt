package br.com.fatec.wave.mobile.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.fatec.wave.mobile.viewmodel.AuthViewModel

@Composable
fun CadastroTela(vm: AuthViewModel, onVoltar: () -> Unit) {
    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }

    Formulario(titulo = "Criar conta", ocupado = vm.ocupado, mensagem = vm.mensagem) {
        Campo("Nome", nome, { nome = it }, !vm.ocupado)
        Campo("E-mail", email, { email = it }, !vm.ocupado)
        Campo("Senha (mínimo 6 caracteres)", senha, { senha = it }, !vm.ocupado, senha = true)
        Button(
            onClick = { vm.cadastrar(nome, email, senha) },
            enabled = !vm.ocupado,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Cadastrar") }
        OutlinedButton(
            onClick = onVoltar,
            enabled = !vm.ocupado,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Voltar ao login") }
    }
}
