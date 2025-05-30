package com.example.pokedexapp.data.remote

data class PokemonListResponse(
    val results: List<Pokemon>
)

data class PokemonSpeciesListResponse(
    val results: List<NamedAPIResource>
)
