package com.example.pokedexapp.data.remote

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeSlot>,
    val stats: List<PokemonStat>,
    val moves: List<MoveSlot> // ✅ Esto se agregó
)

data class TypeSlot(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)

data class PokemonStat(
    val base_stat: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String,
    val url: String
)

data class MoveSlot( // ✅ Nuevo
    val move: MoveName
)

data class MoveName( // ✅ Nuevo
    val name: String,
    val url: String
)
