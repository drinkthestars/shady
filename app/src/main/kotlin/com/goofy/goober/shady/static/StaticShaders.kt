package com.goofy.goober.shady.static

import androidx.compose.runtime.Composable
import com.goofy.goober.shady.ui.HomeScreens
import com.goofy.goober.shady.ui.Scaffold
import com.goofy.goober.shady.ui.Screen

private val Screens = listOf(
    Screen("Noise Grain 1") { },
    Screen("Noise Grain 2") { },
    Screen("Risograph") { },
    Screen("Paper Texture") { PaperTextureContent() },
)

@Composable
fun StaticShaders() {
    Scaffold(
        home = HomeScreens.StaticShaders,
        screens = Screens
    )
}
