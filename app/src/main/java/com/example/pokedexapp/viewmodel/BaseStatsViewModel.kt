package com.example.pokedexapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.remote.PokemonDetail
import com.example.pokedexapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BaseStatsViewModel : ViewModel() {

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail: StateFlow<PokemonDetail?> = _pokemonDetail

    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            try {
                val detail = RetrofitInstance.api.getPokemonDetail(name)
                _pokemonDetail.value = detail
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
