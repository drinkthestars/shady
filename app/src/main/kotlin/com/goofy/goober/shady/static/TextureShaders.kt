package com.goofy.goober.shady.static

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.goofy.goober.shady.ui.Content
import com.goofy.goober.shady.ui.HomeScreens
import com.goofy.goober.shady.ui.Screen

private val Screens = listOf(
    Screen("Noise Grain 1") { NoiseGrain1Texture() },
    Screen("Noise Grain 2") { NoiseGrain2Texture() },
    Screen("Risograph") { RisographTexture() },
    Screen("Paper Texture") { PaperTexture() },
    Screen("Sketching Paper Texture") { SketchingPaperTexture() },
    Screen("Marbled Texture") { MarbledTexture() },
)

@Composable
fun TextureShaders() {
    val navController = rememberNavController()

    Content(
        home = HomeScreens.TextureShaders,
        screens = Screens,
        navController = navController
    )
}
