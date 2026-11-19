package br.com.fatec.wave.mobile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fatec.wave.mobile.viewmodel.CatalogoViewModel

@Composable
fun MusicasTela(vm: CatalogoViewModel, onVoltar: () -> Unit, onAbrir: (Long) -> Unit) {
    var busca by rememberSaveable { mutableStateOf("") }

    // Só carrega se ainda não houver lista (ao voltar do detalhe, mantém a busca)
    LaunchedEffect(Unit) {
        if (vm.musicas.isEmpty()) vm.listarMusicas()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CabecalhoTela("Músicas", onVoltar)
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = busca,
                onValueChange = { busca = it },
                label = { Text("Título") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { vm.buscarMusicas(busca) },
                enabled = !vm.ocupado,
                modifier = Modifier.padding(start = 8.dp)
            ) { Text("Buscar") }
        }
        if (vm.ocupado) LinearProgressIndicator(Modifier.fillMaxWidth())
        Mensagem(vm.mensagem)
        if (!vm.ocupado && vm.musicas.isEmpty()) {
            Text("Nenhuma música encontrada")
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(vm.musicas) { musica ->
                ItemCard(
                    titulo = musica.titulo ?: "",
                    subtitulo = juntar(
                        musica.artistaNome,
                        formatar(musica.genero),
                        musica.anoLancamento?.toString()
                    ),
                    onClick = { musica.id?.let(onAbrir) }
                )
            }
        }
    }
}
