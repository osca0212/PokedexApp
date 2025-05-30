package com.example.pokedexapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pokedexapp.data.remote.PokemonDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import kotlinx.coroutines.launch


@Composable
fun PokemonSearchScreen() {
    var query by remember { mutableStateOf("") }
    var pokemon by remember { mutableStateOf<PokemonDetail?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it.lowercase()
                error = null
            },
            label = { Text("Search Pokémon by name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        val coroutineScope = rememberCoroutineScope()

        Button(onClick = {
            error = null
            pokemon = null
            if (query.isNotBlank()) {
                coroutineScope.launch {
                    try {
                        pokemon = withContext(Dispatchers.IO) {
                            Retrofit.Builder()
                                .baseUrl("https://pokeapi.co/api/v2/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(DetailApi::class.java)
                                .getPokemon(query)
                        }
                    } catch (e: Exception) {
                        error = "Pokémon not found"
                    }
                }
            }
        }) {
            Text("Search")
        }


        Spacer(modifier = Modifier.height(24.dp))

        when {
            error != null -> Text(error ?: "", color = MaterialTheme.colorScheme.error)
            pokemon != null -> {
                val imgUrl =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon!!.id}.png"
                Image(
                    painter = rememberAsyncImagePainter(model = imgUrl),
                    contentDescription = pokemon!!.name,
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text("Name: ${pokemon!!.name.replaceFirstChar { it.uppercase() }}")
                Text("Height: ${pokemon!!.height / 10f} m")
                Text("Weight: ${pokemon!!.weight / 10f} kg")
                Text("Types: ${pokemon!!.types.joinToString { it.type.name }}")
            }
        }
    }
}


