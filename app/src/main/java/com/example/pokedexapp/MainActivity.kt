package com.example.pokedexapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.pokedexapp.navigation.AppNavGraph
import com.example.pokedexapp.ui.theme.PokedexAppTheme

@OptIn(ExperimentalMaterial3Api::class) // para que funcione la topappbar
class MainActivity : ComponentActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar el MediaPlayer con el archivo de música
        mediaPlayer = MediaPlayer.create(this, R.raw.tema_principal)
        mediaPlayer.isLooping = true // Para que la música se repita

        setContent {
            PokedexAppTheme {
                val navController = rememberNavController()
                var isPlaying by remember { mutableStateOf(false) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Pokedex App") },
                            actions = {
                                IconButton(onClick = {
                                    if (isPlaying) {
                                        mediaPlayer.pause()
                                    } else {
                                        mediaPlayer.start()
                                    }
                                    isPlaying = !isPlaying
                                }) {
                                    Icon(
                                        imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                        contentDescription = if (isPlaying) "Pausar Música" else "Reproducir Música"
                                    )
                                }
                            }
                        )
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        AppNavGraph(navController = navController)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
