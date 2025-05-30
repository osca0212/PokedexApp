package com.example.pokedexapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pokedexapp.data.remote.EvolutionChainLink
import com.example.pokedexapp.ui.theme.PokeDarkGray
import com.example.pokedexapp.ui.theme.PokeRed
import com.example.pokedexapp.ui.theme.PokeYellow
import com.example.pokedexapp.viewmodel.EvolutionViewModel

@Composable
<<<<<<< HEAD
@OptIn(ExperimentalMaterial3Api::class) // para que funcione la topappbar

fun EvolutionDetailScreen(viewModel: EvolutionViewModel, pokemonId: String) {
    LaunchedEffect(pokemonId) {
        viewModel.fetchEvolutionChain(pokemonId)
    }

=======
fun EvolutionDetailScreen(viewModel: EvolutionViewModel, pokemonId: String) {
    // Usar pokemonId si es necesario
>>>>>>> e21c23b45eed79d14ab38a54396966bc977cdc61
    val evolutionChain by viewModel.evolutionChain.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadena Evolutiva") },
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
            evolutionChain?.let {
                EvolutionChainView(it)
            } ?: CircularProgressIndicator(color = PokeYellow)
        }
    }
}


@Composable
fun EvolutionChainView(chain: EvolutionChainLink) {
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${extractPokemonId(chain.species.url)}.png"

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = chain.species.name,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = chain.species.name.replaceFirstChar { it.uppercase() },
            color = PokeYellow,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        chain.evolves_to.forEach {
            EvolutionChainView(it)
        }
    }
}

fun extractPokemonId(url: String): String {
    return url.trimEnd('/').split("/").last()
}
