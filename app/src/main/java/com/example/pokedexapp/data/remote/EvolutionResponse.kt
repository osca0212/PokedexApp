package com.example.pokedexapp.data.remote

data class EvolutionChainResponse(
    val id: Int,
    val chain: EvolutionChainLink
)

data class EvolutionChainLink(
    val species: NamedAPIResource,
    val evolves_to: List<EvolutionChainLink>
)

data class NamedAPIResource(
    val name: String,
    val url: String
)
