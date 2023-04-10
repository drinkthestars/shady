package com.goofy.goober.shady.ui

import com.goofy.goober.shady.animated.animatedShadersGraph
import com.goofy.goober.shady.static.textureShadersGraph
import com.goofy.goober.shady.ui.HomeScreens.AnimatedShaders
import com.goofy.goober.shady.ui.HomeScreens.TextureShaders

object HomeScreens {
    const val Home = "Shady"
    const val TextureShaders = "Texture Shaders"
    const val AnimatedShaders = "Animated Shaders"
}

val TopLevelScreens = listOf(
    NestedNavScreen(TextureShaders) { onNavigate ->  textureShadersGraph(onNavigate) },
    NestedNavScreen(AnimatedShaders) { onNavigate -> animatedShadersGraph(onNavigate) },
)
