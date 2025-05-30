package com.example.pokedexapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
<<<<<<< HEAD
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
=======
>>>>>>> e21c23b45eed79d14ab38a54396966bc977cdc61
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.pokedexapp.data.remote.PokemonDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.pokedexapp.ui.theme.PokeDarkGray
import com.example.pokedexapp.ui.theme.PokeRed
import com.example.pokedexapp.ui.theme.PokeYellow

@Composable
<<<<<<< HEAD
@OptIn(ExperimentalMaterial3Api::class) // para que funcione la topappbar
=======
>>>>>>> e21c23b45eed79d14ab38a54396966bc977cdc61
fun PokemonDetailScreen(pokemonId: String, navController: NavHostController) {
    var pokemon by remember { mutableStateOf<PokemonDetail?>(null) }

    LaunchedEffect(pokemonId) {
        pokemon = withContext(Dispatchers.IO) {
            Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DetailApi::class.java)
                .getPokemon(pokemonId)
        }
    }

    pokemon?.let { pkmn ->
        val typeColor = getTypeColor(pkmn.types.firstOrNull()?.type?.name ?: "")

<<<<<<< HEAD
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
=======
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(typeColor)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val imageUrl =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pkmn.id}.png"

                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = pkmn.name,
                        modifier = Modifier.size(150.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = pkmn.name.replaceFirstChar { it.uppercase() },
                        fontSize = 24.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Height: ${pkmn.height / 10f} m", fontSize = 16.sp)
                    Text("Weight: ${pkmn.weight / 10f} kg", fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Types:", fontSize = 18.sp)
                    pkmn.types.forEach {
>>>>>>> e21c23b45eed79d14ab38a54396966bc977cdc61
                        Text(
                            text = pkmn.name.replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = PokeYellow)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = PokeRed,
                        titleContentColor = PokeYellow
                    )
                )
            },
            containerColor = typeColor
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = PokeDarkGray)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val imageUrl =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pkmn.id}.png"

                        Image(
                            painter = rememberAsyncImagePainter(model = imageUrl),
                            contentDescription = pkmn.name,
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Altura: ${pkmn.height / 10f} m", fontSize = 16.sp, color = Color.White)
                        Text(text = "Peso: ${pkmn.weight / 10f} kg", fontSize = 16.sp, color = Color.White)

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Tipos:", fontSize = 18.sp, color = Color.White)
                        pkmn.types.forEach {
                            Text(
                                text = it.type.name.replaceFirstChar { c -> c.uppercase() },
                                fontSize = 16.sp,
                                color = getTypeColor(it.type.name)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = { navController.navigate("evolution_detail/${pkmn.id}") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PokeRed,
                                contentColor = PokeYellow
                            ),
                            shape = RoundedCornerShape(12.dp),
                            elevation = ButtonDefaults.buttonElevation(8.dp)
                        ) {
                            Text("Ver Evoluciones")
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                val firstMoveName = pkmn.moves.firstOrNull()?.move?.name ?: return@Button
                                navController.navigate("move_detail/$firstMoveName")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PokeRed,
                                contentColor = PokeYellow
                            ),
                            shape = RoundedCornerShape(12.dp),
                            elevation = ButtonDefaults.buttonElevation(8.dp)
                        ) {
                            Text("Ver Primer Movimiento")
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { navController.navigate("base_stats/${pkmn.name}") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PokeRed,
                                contentColor = PokeYellow
                            ),
                            shape = RoundedCornerShape(12.dp),
                            elevation = ButtonDefaults.buttonElevation(8.dp)
                        ) {
                            Text("Ver Estadísticas Base")
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // 🔁 Botón de Evolución
                    Button(onClick = {
                        navController.navigate("evolution_detail/${pkmn.id}")
                    }) {
                        Text("Ver Evoluciones")
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // ⚡ Botón de Movimiento
                    Button(onClick = {
                        val firstMoveName = pkmn.moves.firstOrNull()?.move?.name ?: return@Button
                        navController.navigate("move_detail/$firstMoveName")
                    }) {
                        Text("Ver Primer Movimiento")
                    }
                }
            }
        }
    }
}

fun getTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "fire" -> Color(0xFFFFA756)
        "water" -> Color(0xFF58ABF6)
        "grass" -> Color(0xFF8BBE8A)
        "electric" -> Color(0xFFF2CB55)
        "psychic" -> Color(0xFFEA5D60)
        "ice" -> Color(0xFF91D8DF)
        "dragon" -> Color(0xFF7383B9)
        "dark" -> Color(0xFF6F6E78)
        "fairy" -> Color(0xFFEB8FE6)
        "normal" -> Color(0xFFB5B9C4)
        "fighting" -> Color(0xFFEB4971)
        "flying" -> Color(0xFF83A2E3)
        "poison" -> Color(0xFF9F6E97)
        "ground" -> Color(0xFFF78551)
        "rock" -> Color(0xFFD4C294)
        "bug" -> Color(0xFF8BD674)
        "ghost" -> Color(0xFF8571BE)
        "steel" -> Color(0xFF4C91B2)
        else -> Color.LightGray
    }
}

interface DetailApi {
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String): PokemonDetail
}
