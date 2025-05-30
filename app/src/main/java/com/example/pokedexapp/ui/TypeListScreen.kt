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
import androidx.compose.foundation.clickable
import androidx.navigation.NavController


@Composable
fun TypeListScreen(navController: NavController) {
    var types by remember { mutableStateOf<List<PokemonType>>(emptyList()) }

    LaunchedEffect(Unit) {
        types = withContext(Dispatchers.IO) {
            Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TypeApi::class.java)
                .getTypes().results
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tipos de Pokémon", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(types) { type ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("type_detail/${type.name}")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box(
                        modifier = Modifier.padding(16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(text = type.name.replaceFirstChar { it.uppercase() })
                    }
                }


            }
        }
    }
}

data class TypeResponse(
    val results: List<PokemonType>
)

data class PokemonType(
    val name: String,
    val url: String
)

interface TypeApi {
    @GET("type")
    suspend fun getTypes(): TypeResponse
}
