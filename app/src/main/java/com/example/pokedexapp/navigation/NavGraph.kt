package com.example.pokedexapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokedexapp.ui.PokemonListScreen
import com.example.pokedexapp.ui.PokemonDetailScreen
import com.example.pokedexapp.ui.BaseStatsScreen
import com.example.pokedexapp.ui.PokemonSearchScreen
import com.example.pokedexapp.ui.TypeListScreen
import com.example.pokedexapp.ui.TypeDetailScreen
import com.example.pokedexapp.viewmodel.BaseStatsViewModel
import com.example.pokedexapp.viewmodel.EvolutionViewModel
import com.example.pokedexapp.viewmodel.MoveViewModel
import com.example.pokedexapp.ui.EvolutionDetailScreen
import com.example.pokedexapp.ui.MoveDetailScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            PokemonListScreen(navController = navController)
        }

        composable("search") {
            PokemonSearchScreen()
        }

        composable("detail/{pokemonId}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId")
            pokemonId?.let {
                PokemonDetailScreen(it, navController)
            }
        }

        composable("types") {
            TypeListScreen(navController = navController)
        }

        composable("type_detail/{typeName}") { backStackEntry ->
            val typeName = backStackEntry.arguments?.getString("typeName")
            typeName?.let {
                TypeDetailScreen(it)
            }
        }

        composable("base_stats/{pokemonName}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("pokemonName") ?: ""
            val viewModel: BaseStatsViewModel = viewModel()
            BaseStatsScreen(viewModel = viewModel, pokemonName = name)
        }

        // 🛠️ Aquí corregimos los nombres de parámetros para que coincidan con navigate(...)
        composable("evolution_detail/{id}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("id") ?: ""
            val viewModel: EvolutionViewModel = viewModel()
            EvolutionDetailScreen(viewModel = viewModel, pokemonId = pokemonId)
        }

        composable("move_detail/{name}") { backStackEntry ->
            val moveName = backStackEntry.arguments?.getString("name") ?: ""
            val viewModel: MoveViewModel = viewModel()
            MoveDetailScreen(viewModel = viewModel, moveName = moveName)
        }
<<<<<<< HEAD

        composable("base_stats/{pokemonName}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("pokemonName") ?: ""
            val viewModel: BaseStatsViewModel = viewModel()
            BaseStatsScreen(viewModel = viewModel, pokemonName = name)
        }

=======
>>>>>>> e21c23b45eed79d14ab38a54396966bc977cdc61
    }
}

