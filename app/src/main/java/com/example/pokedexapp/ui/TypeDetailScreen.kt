package com.example.pokedexapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

@Composable
fun TypeDetailScreen(typeName: String) {
    var pokemonList by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(typeName) {
        pokemonList = withContext(Dispatchers.IO) {
            Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TypeDetailApi::class.java)
                .getTypeDetail(typeName)
                .pokemon.map { it.pokemon.name }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Pokémon de tipo ${typeName.replaceFirstChar { it.uppercase() }}",
            style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(pokemonList) { name ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(
                        text = name.replaceFirstChar { it.uppercase() },
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

data class TypeDetailResponse(
    val pokemon: List<PokemonEntry>
)

data class PokemonEntry(
    val pokemon: PokemonName
)

data class PokemonName(
    val name: String,
    val url: String
)

interface TypeDetailApi {
    @GET("type/{typeName}")
    suspend fun getTypeDetail(@Path("typeName") typeName: String): TypeDetailResponse
}
