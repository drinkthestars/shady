package com.goofy.goober.shady.animated

import androidx.compose.runtime.Composable
import com.goofy.goober.shady.ui.HomeScreens
import com.goofy.goober.shady.ui.Scaffold
import com.goofy.goober.shady.ui.Screen

private val Screens = listOf(
    Screen("Gradient") {  },
)

@Composable
fun AnimatedShaders() {
    Scaffold(
        home = HomeScreens.AnimatedShaders,
        screens = Screens
    )
}
