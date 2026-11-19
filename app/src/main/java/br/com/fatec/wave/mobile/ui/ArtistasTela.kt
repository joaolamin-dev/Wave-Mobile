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
fun ArtistasTela(vm: CatalogoViewModel, onVoltar: () -> Unit, onAbrir: (Long) -> Unit) {
    var busca by rememberSaveable { mutableStateOf("") }

    // Só carrega se ainda não houver lista
    LaunchedEffect(Unit) {
        if (vm.artistas.isEmpty()) vm.listarArtistas()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CabecalhoTela("Artistas", onVoltar)
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = busca,
                onValueChange = { busca = it },
                label = { Text("Nome") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { vm.buscarArtistas(busca) },
                enabled = !vm.ocupado,
                modifier = Modifier.padding(start = 8.dp)
            ) { Text("Buscar") }
        }
        if (vm.ocupado) LinearProgressIndicator(Modifier.fillMaxWidth())
        Mensagem(vm.mensagem)
        if (!vm.ocupado && vm.artistas.isEmpty()) {
            Text("Nenhum artista encontrado")
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(vm.artistas) { artista ->
                ItemCard(
                    titulo = artista.nome ?: "",
                    subtitulo = juntar(formatar(artista.tipo), artista.nacionalidade),
                    onClick = { artista.id?.let(onAbrir) }
                )
            }
        }
    }
}
