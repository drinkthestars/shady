package com.goofy.goober.shady.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

sealed interface Screen {
    val title: String
    val description: String?
}

data class NestedNavScreen(
    override val title: String,
    override val description: String? = null,
    val nestedGraph: NavGraphBuilder.(onNavigate: (Screen) -> Unit) -> Unit
): Screen

data class DestinationScreen(
    override val title: String,
    override val description: String? = null,
    val content: @Composable () -> Unit
): Screen


fun NavGraphBuilder.nestedContent(
    onNavigate: (Screen) -> Unit,
    screens: List<DestinationScreen>,
    home: String
) {
    composable(home) {
        List(
            screens = screens,
            onClick = { screen ->
                onNavigate(screen)
            }
        )
    }
    screens.forEach { screen ->
        composable(screen.title) { screen.content() }
    }
}
