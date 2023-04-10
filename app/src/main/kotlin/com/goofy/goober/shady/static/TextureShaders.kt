package com.goofy.goober.shady.static

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.goofy.goober.shady.ui.DestinationScreen
import com.goofy.goober.shady.ui.HomeScreens
import com.goofy.goober.shady.ui.List
import com.goofy.goober.shady.ui.Screen
import com.goofy.goober.shady.ui.nestedContent

private val Screens = listOf(
    DestinationScreen(
        title = "Noise Grain 1",
        description = "Subtle noise grain"
    ) { NoiseGrain1Texture() },
    DestinationScreen(
        title = "Noise Grain 2",
        description = "Noisier grain"
    ) { NoiseGrain2Texture() },
    DestinationScreen(title = "Risograph", description = "Risograph print") { RisographTexture() },
    DestinationScreen(
        title = "Paper Texture",
        description = "Like noise grain, but more like paper"
    ) { PaperTexture() },
    DestinationScreen(
        title = "Sketching Paper Texture",
        description = "Texture of rough/sketchpad paper"
    ) { SketchingPaperTexture() },
    DestinationScreen(
        title = "Marbled Texture",
        description = "A weird watery/marbled texture"
    ) { MarbledTexture() },
)

fun NavGraphBuilder.textureShadersGraph(onNavigate: (Screen) -> Unit) {
    nestedContent(onNavigate, screens = Screens, home = HomeScreens.TextureShaders)
}
