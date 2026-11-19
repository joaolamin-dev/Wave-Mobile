package br.com.fatec.wave.mobile.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fatec.wave.mobile.viewmodel.PerfilViewModel

@Composable
fun PerfilTela(vm: PerfilViewModel, onVoltar: () -> Unit) {
    val context = LocalContext.current

    // Atualiza perfil e playlists ao abrir a tela
    LaunchedEffect(Unit) { vm.carregar() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CabecalhoTela("Meu perfil", onVoltar)
        if (vm.ocupado) LinearProgressIndicator(Modifier.fillMaxWidth())
        Mensagem(vm.mensagem)

        val perfil = vm.perfil
        if (perfil != null) {
            Text(
                perfil.nome ?: "",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text("E-mail: ${perfil.email ?: "-"}")
            Text("Papel: ${formatar(perfil.role)}")
        }

        Text("Minhas playlists", style = MaterialTheme.typography.titleLarge)
        if (!vm.ocupado && vm.playlists.isEmpty()) {
            Text("Você ainda não tem playlists.")
        }
        // Um Card por playlist, com os títulos das músicas
        vm.playlists.forEach { playlist ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text(playlist.nome ?: "", style = MaterialTheme.typography.titleMedium)
                    playlist.musicas.orEmpty().forEach { musica ->
                        Text(
                            "• ${musica.titulo ?: ""}",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                val site = Intent(Intent.ACTION_VIEW, Uri.parse("https://wave-web-s3te.onrender.com"))
                context.startActivity(site)
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Abrir Wave na web") }
    }
}
