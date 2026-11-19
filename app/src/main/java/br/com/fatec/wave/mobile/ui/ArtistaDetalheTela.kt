package br.com.fatec.wave.mobile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fatec.wave.mobile.viewmodel.CatalogoViewModel

@Composable
fun ArtistaDetalheTela(vm: CatalogoViewModel, id: Long, onVoltar: () -> Unit) {
    LaunchedEffect(id) { vm.abrirArtista(id) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CabecalhoTela("Artista", onVoltar)
        if (vm.ocupado) LinearProgressIndicator(Modifier.fillMaxWidth())
        Mensagem(vm.mensagem)

        val artista = vm.artistaAtual
        if (artista != null) {
            Text(
                artista.nome ?: "",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text("Tipo: ${formatar(artista.tipo)}")
            Text("Nacionalidade: ${artista.nacionalidade ?: "-"}")
        }
    }
}
