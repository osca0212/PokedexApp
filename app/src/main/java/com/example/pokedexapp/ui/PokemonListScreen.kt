package com.example.pokedexapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pokedexapp.data.remote.Pokemon
import com.example.pokedexapp.viewmodel.PokemonViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.pokedexapp.data.remote.id

// 🎨 Colores personalizados estilo Pokédex
val PokeRed = Color(0xFFCC0000)
val PokeYellow = Color(0xFFFFCC00)
val PokeDarkGray = Color(0xFF333333)
@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun PokemonListScreen(
    viewModel: PokemonViewModel = viewModel(),
    navController: NavController
) {
    val pokemonList by viewModel.pokemonList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pokédex", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PokeRed,
                    titleContentColor = PokeYellow
                ),
                actions = {
                    IconButton(onClick = { navController.navigate("search") }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = PokeYellow
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // 🔘 Botón Buscar
            Button(
                onClick = { navController.navigate("search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PokeRed,
                    contentColor = PokeYellow
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text("Buscar Pokémon")
            }

            // 🔘 Botón Ver tipos
            Button(
                onClick = { navController.navigate("types") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PokeRed,
                    contentColor = PokeYellow
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text("Ver Tipos de Pokémon")
            }

            // 🧾 Lista de Pokémon
            LazyColumn {
                items(pokemonList) { pokemon ->
                    PokemonItem(pokemon = pokemon, navController = navController)
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, navController: NavController) {
    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png"

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = PokeDarkGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                navController.navigate("detail/${pokemon.id}")
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = pokemon.name,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}
