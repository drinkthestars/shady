package com.goofy.goober.shady.gesture

import androidx.compose.runtime.Composable
import com.goofy.goober.shady.ui.HomeScreens
import com.goofy.goober.shady.ui.Scaffold
import com.goofy.goober.shady.ui.Screen

private val Screens = listOf(
    Screen("Blob") { },
    Screen("Starfield") { },
)

@Composable
fun GestureShaders() {
    Scaffold(
        home = HomeScreens.GestureShaders,
        screens = Screens
    )
}
