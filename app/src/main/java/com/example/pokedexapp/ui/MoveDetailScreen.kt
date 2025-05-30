package com.example.pokedexapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedexapp.ui.theme.PokeDarkGray
import com.example.pokedexapp.ui.theme.PokeRed
import com.example.pokedexapp.ui.theme.PokeYellow
import com.example.pokedexapp.viewmodel.MoveViewModel

@Composable
<<<<<<< HEAD
@OptIn(ExperimentalMaterial3Api::class) // para que funcione la topappbar

fun MoveDetailScreen(viewModel: MoveViewModel, moveName: String) {
    LaunchedEffect(moveName) {
        viewModel.fetchMoveDetail(moveName)
    }

=======
fun MoveDetailScreen(viewModel: MoveViewModel, moveName: String) {
    // Usar moveName para cargar los datos si hace falta
>>>>>>> e21c23b45eed79d14ab38a54396966bc977cdc61
    val moveDetail by viewModel.moveDetail.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Movimiento") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PokeRed,
                    titleContentColor = PokeYellow
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(PokeDarkGray)
                .padding(16.dp),
        ) {
            moveDetail?.let { move ->
                val textStyle = MaterialTheme.typography.bodyLarge.copy(color = PokeYellow)

                Text("Nombre: ${move.name.replaceFirstChar { it.uppercase() }}", style = textStyle)
                Text("Tipo: ${move.type.name.replaceFirstChar { it.uppercase() }}", style = textStyle)
                Text("Clase de Daño: ${move.damage_class.name.replaceFirstChar { it.uppercase() }}", style = textStyle)
                Text("Poder: ${move.power ?: "N/A"}", style = textStyle)
                Text("Precisión: ${move.accuracy ?: "N/A"}", style = textStyle)
                Text("PP: ${move.pp}", style = textStyle)
                Text(
                    "Efecto: ${move.effect_entries.firstOrNull { it.language.name == "es" }?.effect ?: "N/A"}",
                    style = textStyle
                )
            } ?: CircularProgressIndicator(color = PokeYellow)
        }
    }
}

