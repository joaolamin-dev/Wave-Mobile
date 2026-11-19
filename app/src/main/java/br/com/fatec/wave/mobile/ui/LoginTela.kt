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
fun LoginTela(vm: AuthViewModel, onCriarConta: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }

    Formulario(titulo = "Wave", ocupado = vm.ocupado, mensagem = vm.mensagem) {
        Campo("E-mail", email, { email = it }, !vm.ocupado)
        Campo("Senha", senha, { senha = it }, !vm.ocupado, senha = true)
        Button(
            onClick = { vm.login(email, senha) },
            enabled = !vm.ocupado,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Entrar") }
        OutlinedButton(
            onClick = onCriarConta,
            enabled = !vm.ocupado,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Criar conta") }
    }
}
