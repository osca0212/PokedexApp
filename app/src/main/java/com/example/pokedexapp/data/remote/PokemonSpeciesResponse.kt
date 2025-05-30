package com.example.pokedexapp.data.remote

data class PokemonSpeciesResponse(
    val name: String,
    val habitat: NamedAPIResource?,
    val generation: NamedAPIResource,
    val is_legendary: Boolean,
    val is_mythical: Boolean,
    val color: NamedAPIResource,
    val evolution_chain: EvolutionChainReference
)

data class EvolutionChainReference(
    val url: String
)
