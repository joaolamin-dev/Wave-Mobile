package br.com.fatec.wave.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import br.com.fatec.wave.mobile.ui.AppWave
import br.com.fatec.wave.mobile.ui.theme.WaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaveTheme {
                Surface(Modifier.fillMaxSize()) {
                    // Respeita barras do sistema e teclado
                    Box(Modifier.fillMaxSize().safeDrawingPadding()) {
                        AppWave()
                    }
                }
            }
        }
    }
}
