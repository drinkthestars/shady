package com.goofy.goober.shady.ui

import com.goofy.goober.shady.animated.AnimatedShaders
import com.goofy.goober.shady.gesture.GestureShaders
import com.goofy.goober.shady.static.StaticShaders

object HomeScreens {
    const val Home = "Home"
    const val StaticShaders = "Static Shaders"
    const val AnimatedShaders = "Animated Shaders"
    const val GestureShaders = "Gesture Shaders"
}

val TopLevelScreens = listOf(
    Screen("Static Shaders") { StaticShaders() },
    Screen("Animated Shaders") { AnimatedShaders() },
    Screen("Gesture Shaders") { GestureShaders() },
)
