package com.example.pokedexapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedexapp.ui.theme.PokeDarkGray
import com.example.pokedexapp.ui.theme.PokeRed
import com.example.pokedexapp.ui.theme.PokeYellow
import com.example.pokedexapp.viewmodel.BaseStatsViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class) // para que funcione la topappbar

fun BaseStatsScreen(viewModel: BaseStatsViewModel, pokemonName: String) {
    val detail by viewModel.pokemonDetail.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPokemonDetail(pokemonName)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Estadísticas Base") },
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            detail?.let {
                it.stats.forEach { stat ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = PokeRed),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "${stat.stat.name.uppercase()}: ${stat.base_stat}",
                            modifier = Modifier.padding(16.dp),
                            color = PokeYellow
                        )
                    }
                }
            } ?: CircularProgressIndicator(color = PokeYellow)
        }
    }
}
