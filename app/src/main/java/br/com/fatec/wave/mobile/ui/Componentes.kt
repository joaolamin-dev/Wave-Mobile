package br.com.fatec.wave.mobile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

// Tela de formulário rolável, que sobe junto com o teclado
@Composable
fun Formulario(
    titulo: String,
    ocupado: Boolean,
    mensagem: String?,
    conteudo: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        if (ocupado) LinearProgressIndicator(Modifier.fillMaxWidth())
        Mensagem(mensagem)
        conteudo()
    }
}

// Campo de texto padrão do app
@Composable
fun Campo(
    rotulo: String,
    valor: String,
    mudar: (String) -> Unit,
    habilitado: Boolean = true,
    senha: Boolean = false
) {
    OutlinedTextField(
        value = valor,
        onValueChange = mudar,
        label = { Text(rotulo) },
        enabled = habilitado,
        singleLine = true,
        visualTransformation = if (senha) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (senha) KeyboardType.Password else KeyboardType.Text
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

// Título da tela com botão de voltar
@Composable
fun CabecalhoTela(titulo: String, onVoltar: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextButton(onClick = onVoltar) { Text("Voltar") }
        Text(titulo, style = MaterialTheme.typography.titleLarge)
    }
}

// Card clicável com título e subtítulo
@Composable
fun ItemCard(titulo: String, subtitulo: String, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(titulo, style = MaterialTheme.typography.titleMedium)
            if (subtitulo.isNotBlank()) {
                Text(
                    subtitulo,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// Mostra a mensagem do ViewModel, se houver
@Composable
fun Mensagem(mensagem: String?) {
    if (mensagem != null) {
        Text(mensagem, color = MaterialTheme.colorScheme.secondary)
    }
}

// Nomes de exibição dos enums da API (gênero, tipo e papel)
fun formatar(texto: String?): String = when (texto) {
    "POP" -> "Pop"
    "ROCK" -> "Rock"
    "HIP_HOP" -> "Hip Hop"
    "ELETRONICA" -> "Eletrônica"
    "MPB" -> "MPB"
    "SERTANEJO" -> "Sertanejo"
    "JAZZ" -> "Jazz"
    "CLASSICA" -> "Clássica"
    "OUTROS" -> "Outros"
    "SOLO" -> "Solo"
    "DUO" -> "Duo"
    "BANDA" -> "Banda"
    "ADMIN" -> "Administrador"
    "USER" -> "Usuário"
    // Valor desconhecido: ALGUM_VALOR -> "Algum Valor"
    else -> texto.orEmpty().split("_").joinToString(" ") { parte ->
        parte.lowercase().replaceFirstChar { it.uppercase() }
    }
}

// Junta as partes não vazias com " · "
fun juntar(vararg partes: String?): String =
    partes.filter { !it.isNullOrBlank() }.joinToString(" · ")
