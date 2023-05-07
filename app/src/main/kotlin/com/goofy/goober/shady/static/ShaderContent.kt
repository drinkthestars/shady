package com.goofy.goober.shady.static

import android.content.res.Configuration
import android.graphics.RenderEffect
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goofy.goober.shaders.MarbledTexture
import com.goofy.goober.shaders.NoiseGrain1
import com.goofy.goober.shaders.NoiseGrain2
import com.goofy.goober.shaders.PaperTexture
import com.goofy.goober.shaders.Risograph
import com.goofy.goober.shaders.SketchingPaperTexture
import com.goofy.goober.shady.R
import com.goofy.goober.style.ShadyContainer
import com.goofy.goober.style.ShadyTheme
import com.goofy.goober.style.Slider

@Composable
fun MarbledTexture(modifier: Modifier = Modifier) {
    ShadyContainer(
        modifier = modifier,
        content = {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged { size ->
                        MarbledTexture.setFloatUniform(
                            "resolution",
                            size.width.toFloat(),
                            size.height.toFloat()
                        )
                    }
                    .graphicsLayer {
                        renderEffect = RenderEffect
                            .createRuntimeShaderEffect(
                                MarbledTexture,
                                "image"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
//            Slider()
//            Spacer(modifier = Modifier.height(24.dp))
//            Slider()
        }
    )
}

@Composable
fun PaperTexture(modifier: Modifier = Modifier) {
    var grain by remember { mutableStateOf(0.05f) }
    var fiber by remember { mutableStateOf(0.5f) }

    ShadyContainer(
        modifier = modifier,
        content = {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged { size ->
                        PaperTexture.setFloatUniform(
                            "resolution",
                            size.width.toFloat(),
                            size.height.toFloat()
                        )
                    }
                    .graphicsLayer {
                        PaperTexture.setFloatUniform("grainIntensity", grain)
                        PaperTexture.setFloatUniform("fiberIntensity", fiber)
                        renderEffect = RenderEffect
                            .createRuntimeShaderEffect(
                                PaperTexture,
                                "image"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
            Slider(
                label = "Grain = $grain",
                value = grain,
                onValueChange = {
                    grain = it
                },
                valueRange = 0f..1f
            )
            Spacer(modifier = Modifier.height(24.dp))
            Slider(
                label = "Fiber = $fiber",
                value = fiber,
                onValueChange = {
                    fiber = it
                },
                valueRange = 0f..1f
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    )
}

@Composable
fun NoiseGrain2Texture(modifier: Modifier = Modifier) {

    var intensity by remember { mutableStateOf(0.2f) }

    ShadyContainer(
        modifier = modifier,
        content = {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged { size ->
                        NoiseGrain2.setFloatUniform(
                            "resolution",
                            size.width.toFloat(),
                            size.height.toFloat()
                        )
                    }
                    .graphicsLayer {
                        NoiseGrain2.setFloatUniform("intensity", intensity)
                        renderEffect = RenderEffect
                            .createRuntimeShaderEffect(
                                NoiseGrain2,
                                "image"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
            Slider(
                label = "Intensity = $intensity",
                value = intensity,
                onValueChange = {
                    intensity = it
                },
                valueRange = 0f..1f
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    )
}

@Composable
fun NoiseGrain1Texture(modifier: Modifier = Modifier) {

    var intensity by remember { mutableStateOf(0.15f) }

    ShadyContainer(
        modifier = modifier,
        content = {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged { size ->
                        NoiseGrain1.setFloatUniform(
                            "resolution",
                            size.width.toFloat(),
                            size.height.toFloat()
                        )
                    }
                    .graphicsLayer {
                        NoiseGrain1.setFloatUniform("intensity", intensity)
                        renderEffect = RenderEffect
                            .createRuntimeShaderEffect(
                                NoiseGrain1,
                                "image"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
            Slider(
                label = "Intensity = $intensity",
                value = intensity,
                onValueChange = {
                    intensity = it
                },
                valueRange = 0f..1f
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    )
}

@Composable
fun RisographTexture(modifier: Modifier = Modifier) {

    var randomization by remember { mutableStateOf(0.15f) }
    var randomizationOffset by remember { mutableStateOf(0.16f) }

    ShadyContainer(
        modifier = modifier,
        content = {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged { size ->
                        Risograph.setFloatUniform(
                            "resolution",
                            size.width.toFloat(),
                            size.height.toFloat()
                        )
                    }
                    .graphicsLayer {
                        Risograph.setFloatUniform("randomization", randomization)
                        Risograph.setFloatUniform("randomizationOffset", randomizationOffset)
                        renderEffect = RenderEffect
                            .createRuntimeShaderEffect(
                                Risograph,
                                "image"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
            Slider(
                label = "Contrast 1 = $randomization",
                value = randomization,
                onValueChange = {
                    randomization = it
                },
                valueRange = 0.07f..0.8f
            )
            Spacer(modifier = Modifier.height(24.dp))
            Slider(
                label = "Contrast 2 = $randomizationOffset",
                value = randomizationOffset,
                onValueChange = {
                    randomizationOffset = it
                },
                valueRange = 0.0f..0.28f
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    )
}

@Composable
fun SketchingPaperTexture(modifier: Modifier = Modifier) {

    var amount by remember { mutableStateOf(0.15f) }
    var contrast1 by remember { mutableStateOf(2.0f) }
    var contrast2 by remember { mutableStateOf(2.0f) }

    ShadyContainer(
        modifier = modifier,
        content = {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged { size ->
                        SketchingPaperTexture.setFloatUniform(
                            /* uniformName = */ "resolution",
                            /* value1 = */ size.width.toFloat(),
                            /* value2 = */ size.height.toFloat()
                        )
                    }
                    .graphicsLayer {
                        SketchingPaperTexture.setFloatUniform("amount", amount)
                        SketchingPaperTexture.setFloatUniform("contrast1", contrast1)
                        SketchingPaperTexture.setFloatUniform("contrast2", contrast2)
                        renderEffect = RenderEffect
                            .createRuntimeShaderEffect(
                                /* shader = */ SketchingPaperTexture,
                                /* uniformShaderName = */ "image"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
            Slider(
                label = "Contrast 1 = $contrast1",
                value = contrast1,
                onValueChange = {
                    contrast1 = it
                },
                valueRange = 2.0f..9.0f
            )
            Spacer(modifier = Modifier.height(24.dp))
            Slider(
                label = "Contrast 2 = $contrast2",
                value = contrast2,
                onValueChange = {
                    contrast2 = it
                },
                valueRange = 2.0f..9.0f
            )
            Spacer(modifier = Modifier.height(24.dp))
            Slider(
                label = "Amount = $amount",
                value = amount,
                onValueChange = {
                    amount = it
                },
                valueRange = 0f..1.2f
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    )
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_MASK)
fun PaperTextureContentPreview() {
    ShadyTheme {
        SketchingPaperTexture()
    }
}
