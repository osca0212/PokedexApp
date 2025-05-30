package com.example.pokedexapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PokeRed,
    secondary = PokeYellow,
    background = PokeBlack,
    surface = PokeDarkGray,
    onPrimary = PokeYellow,
    onSecondary = PokeBlack,
    onBackground = PokeLightGray,
    onSurface = PokeYellow
)

@Composable
fun PokedexAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PokedexTypography,
        shapes = Shapes(),
        content = content
    )
}
