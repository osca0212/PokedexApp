package com.example.pokedexapp.data.remote

data class MoveDetailResponse(
    val name: String,
    val accuracy: Int?,
    val power: Int?,
    val pp: Int,
    val type: NamedAPIResource,
    val damage_class: NamedAPIResource,
    val effect_entries: List<VerboseEffect>
)

data class VerboseEffect(
    val effect: String,
    val short_effect: String,
    val language: NamedAPIResource
)
