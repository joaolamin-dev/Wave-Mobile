package br.com.fatec.wave.mobile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.fatec.wave.mobile.data.Sessao
import br.com.fatec.wave.mobile.viewmodel.AuthViewModel
import br.com.fatec.wave.mobile.viewmodel.CatalogoViewModel
import br.com.fatec.wave.mobile.viewmodel.PerfilViewModel

@Composable
fun AppWave() {
    val nav = rememberNavController()

    // ViewModels criados uma vez e repassados às telas
    val authVm: AuthViewModel = viewModel()
    val catalogoVm: CatalogoViewModel = viewModel()
    val perfilVm: PerfilViewModel = viewModel()

    // Limpa os dados e volta ao login, apagando a pilha
    fun irParaLogin() {
        catalogoVm.limpar()
        perfilVm.limpar()
        nav.navigate(Login) {
            popUpTo(nav.graph.id) { inclusive = true }
        }
    }

    // Depois do login, vai para o início
    LaunchedEffect(authVm.entrou) {
        if (authVm.entrou) {
            authVm.consumirEntrada()
            nav.navigate(Inicio) {
                popUpTo<Login> { inclusive = true }
            }
        }
    }

    // Depois do cadastro, volta ao login (a mensagem de sucesso aparece lá)
    LaunchedEffect(authVm.cadastrou) {
        if (authVm.cadastrou) {
            authVm.consumirEntrada()
            nav.popBackStack()
        }
    }

    // Token apagado (ex.: 401) fora de Login/Cadastro: volta ao login
    LaunchedEffect(Sessao.token) {
        val destino = nav.currentDestination
        if (Sessao.token == null && destino != null &&
            !destino.hasRoute<Login>() && !destino.hasRoute<Cadastro>()
        ) {
            irParaLogin()
        }
    }

    NavHost(navController = nav, startDestination = Login) {
        composable<Login> {
            LoginTela(
                vm = authVm,
                onCriarConta = {
                    authVm.limparMensagem()
                    nav.navigate(Cadastro)
                }
            )
        }
        composable<Cadastro> {
            CadastroTela(
                vm = authVm,
                onVoltar = {
                    authVm.limparMensagem()
                    nav.popBackStack()
                }
            )
        }
        composable<Inicio> {
            InicioTela(
                vm = perfilVm,
                onMusicas = { nav.navigate(Musicas) },
                onArtistas = { nav.navigate(Artistas) },
                onPerfil = { nav.navigate(Perfil) },
                onSair = {
                    authVm.sair()
                    irParaLogin()
                }
            )
        }
        composable<Musicas> {
            MusicasTela(
                vm = catalogoVm,
                onVoltar = { nav.popBackStack() },
                onAbrir = { id -> nav.navigate(MusicaDetalhe(id)) }
            )
        }
        composable<MusicaDetalhe> { backStackEntry ->
            val rota = backStackEntry.toRoute<MusicaDetalhe>()
            MusicaDetalheTela(vm = catalogoVm, id = rota.id, onVoltar = { nav.popBackStack() })
        }
        composable<Artistas> {
            ArtistasTela(
                vm = catalogoVm,
                onVoltar = { nav.popBackStack() },
                onAbrir = { id -> nav.navigate(ArtistaDetalhe(id)) }
            )
        }
        composable<ArtistaDetalhe> { backStackEntry ->
            val rota = backStackEntry.toRoute<ArtistaDetalhe>()
            ArtistaDetalheTela(vm = catalogoVm, id = rota.id, onVoltar = { nav.popBackStack() })
        }
        composable<Perfil> {
            PerfilTela(vm = perfilVm, onVoltar = { nav.popBackStack() })
        }
    }
}
