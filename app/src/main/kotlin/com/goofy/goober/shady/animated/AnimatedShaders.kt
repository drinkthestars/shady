package com.goofy.goober.shady.animated

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import com.goofy.goober.shady.ui.DestinationScreen
import com.goofy.goober.shady.ui.HomeScreens
import com.goofy.goober.shady.ui.Screen
import com.goofy.goober.shady.ui.nestedContent

private val Screens = listOf(
    DestinationScreen(title = "Animated", description = "") {
        Text("COMING SOON")
    },
)

fun NavGraphBuilder.animatedShadersGraph(onNavigate: (Screen) -> Unit) {
    nestedContent(onNavigate, screens = Screens, home = HomeScreens.AnimatedShaders)
}
