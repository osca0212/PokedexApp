package com.example.pokedexapp.data.remote

data class Pokemon(
    val name: String,
    val url: String
)

val Pokemon.id: Int
    get() = url.trimEnd('/').split("/").last().toInt()
