package com.goofy.goober.shady.animated

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ShaderBrush
import androidx.navigation.NavGraphBuilder
import com.goofy.goober.shaders.GradientShader
import com.goofy.goober.shaders.LightScatteringShader
import com.goofy.goober.shaders.NoodleZoomShader
import com.goofy.goober.shaders.WarpSpeedShader
import com.goofy.goober.shady.ui.DestinationScreen
import com.goofy.goober.shady.ui.HomeScreens
import com.goofy.goober.shady.ui.Screen
import com.goofy.goober.shady.ui.nestedContent
import com.goofy.goober.sketch.SketchWithCache
import com.goofy.goober.style.LargeCardShape

private val Screens = listOf(
    DestinationScreen(title = "Gradient Shader") {
        GradientShader()
    },
    DestinationScreen(title = "Skia Sample Shader") {
        SkiaSampleShader()
    },
    DestinationScreen(title = "Warp Speed Shader") {
        WarpSpeedShader()
    },
)

fun NavGraphBuilder.animatedShadersGraph(onNavigate: (Screen) -> Unit) {
    nestedContent(onNavigate, screens = Screens, home = HomeScreens.AnimatedShaders)
}

@Composable
fun GradientShader(modifier: Modifier = Modifier) {
    val brush = remember { ShaderBrush(GradientShader) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        SketchWithCache(
            speed = 1f,
            modifier = modifier.fillMaxSize(0.8f).clip(LargeCardShape)
        ) { time ->
            GradientShader.setFloatUniform(
                "resolution",
                this.size.width, this.size.height
            )
            GradientShader.setFloatUniform("time", time)
            onDrawBehind {
                drawRect(brush)
            }
        }
    }
}

@Composable
fun SkiaSampleShader(modifier: Modifier = Modifier) {
    val brush = remember { ShaderBrush(NoodleZoomShader) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        SketchWithCache(
            speed = 1f,
            modifier = modifier.fillMaxSize(0.8f).clip(LargeCardShape)
        ) { time ->
            NoodleZoomShader.setFloatUniform(
                "resolution",
                this.size.width, this.size.height
            )
            NoodleZoomShader.setFloatUniform("time", time)
            onDrawBehind {
                drawRect(brush)
            }
        }
    }
}

@Composable
fun WarpSpeedShader(modifier: Modifier = Modifier) {
    val brush = remember { ShaderBrush(WarpSpeedShader) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        SketchWithCache(
            speed = 1f,
            modifier = modifier.fillMaxSize(0.8f).clip(LargeCardShape)
        ) { time ->
            WarpSpeedShader.setFloatUniform(
                "resolution",
                this.size.width, this.size.height
            )
            WarpSpeedShader.setFloatUniform("time", time)
            onDrawBehind {
                drawRect(brush)
            }
        }
    }
}
