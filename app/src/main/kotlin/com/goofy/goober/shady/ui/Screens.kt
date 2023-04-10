package com.goofy.goober.shady.ui

import com.goofy.goober.shady.animated.AnimatedShaders
import com.goofy.goober.shady.static.TextureShaders

object HomeScreens {
    const val Home = "Home"
    const val TextureShaders = "Texture Shaders"
    const val AnimatedShaders = "Animated Shaders"
}

val TopLevelScreens = listOf(
    Screen(HomeScreens.TextureShaders) { TextureShaders() },
    Screen(HomeScreens.AnimatedShaders) { AnimatedShaders() },
)
