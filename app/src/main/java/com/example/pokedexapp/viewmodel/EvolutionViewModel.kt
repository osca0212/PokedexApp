package com.example.pokedexapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.remote.EvolutionChainLink
import com.example.pokedexapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EvolutionViewModel : ViewModel() {

    private val _evolutionChain = MutableStateFlow<EvolutionChainLink?>(null)
    val evolutionChain: StateFlow<EvolutionChainLink?> = _evolutionChain

    fun fetchEvolutionChain(pokemonId: String) {
        viewModelScope.launch {
            try {
                val id = pokemonId.toIntOrNull() ?: return@launch
                val species = RetrofitInstance.api.getPokemonSpecies(id)
                val chainUrl = species.evolution_chain.url
                val chainId = chainUrl.split("/").filter { it.isNotEmpty() }.last().toInt()
                val chainResponse = RetrofitInstance.api.getEvolutionChain(chainId)
                _evolutionChain.value = chainResponse.chain
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
