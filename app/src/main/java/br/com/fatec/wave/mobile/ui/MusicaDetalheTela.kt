package br.com.fatec.wave.mobile.ui

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fatec.wave.mobile.viewmodel.CatalogoViewModel

@Composable
fun MusicaDetalheTela(vm: CatalogoViewModel, id: Long, onVoltar: () -> Unit) {
    val context = LocalContext.current

    LaunchedEffect(id) { vm.abrirMusica(id) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CabecalhoTela("Música", onVoltar)
        if (vm.ocupado) LinearProgressIndicator(Modifier.fillMaxWidth())
        Mensagem(vm.mensagem)

        val musica = vm.musicaAtual
        if (musica != null) {
            Text(
                musica.titulo ?: "",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text("Artista: ${musica.artistaNome ?: "-"}")
            Text("Gênero: ${formatar(musica.genero)}")
            Text("Ano: ${musica.anoLancamento ?: "-"}")
            Button(
                onClick = {
                    // Compartilha um texto simples com outros apps
                    val envio = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "Ouvindo ${musica.titulo} - ${musica.artistaNome} no Wave"
                        )
                    }
                    context.startActivity(Intent.createChooser(envio, "Compartilhar"))
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Compartilhar") }
        }
    }
}
