package com.goofy.goober.shady.animated

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.goofy.goober.shady.ui.Content
import com.goofy.goober.shady.ui.HomeScreens
import com.goofy.goober.shady.ui.Scaffold
import com.goofy.goober.shady.ui.Screen

private val Screens = listOf(
    Screen("Gradient") { },
)

@Composable
fun AnimatedShaders() {
    val navController = rememberNavController()

    Content(
        home = HomeScreens.AnimatedShaders,
        screens = Screens,
        navController = navController
    )
}
