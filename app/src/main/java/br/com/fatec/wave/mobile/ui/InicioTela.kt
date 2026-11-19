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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fatec.wave.mobile.viewmodel.PerfilViewModel

@Composable
fun InicioTela(
    vm: PerfilViewModel,
    onMusicas: () -> Unit,
    onArtistas: () -> Unit,
    onPerfil: () -> Unit,
    onSair: () -> Unit
) {
    // Carrega o perfil só na primeira vez
    LaunchedEffect(Unit) {
        if (vm.perfil == null) vm.carregar()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Olá, ${vm.perfil?.nome ?: ""}",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        if (vm.ocupado) LinearProgressIndicator(Modifier.fillMaxWidth())
        Mensagem(vm.mensagem)
        ItemCard("Músicas", "Explore o catálogo", onMusicas)
        ItemCard("Artistas", "Conheça os artistas", onArtistas)
        ItemCard("Meu perfil", "Seus dados e playlists", onPerfil)
        OutlinedButton(onClick = onSair, modifier = Modifier.fillMaxWidth()) { Text("Sair") }
    }
}
