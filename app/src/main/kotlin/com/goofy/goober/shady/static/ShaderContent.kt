package com.goofy.goober.shady.static

import android.content.res.Configuration
import android.graphics.RenderEffect
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
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
                                "contents"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
            Slider()
            Spacer(modifier = Modifier.height(24.dp))
            Slider()
        }
    )
}

@Composable
fun PaperTexture(modifier: Modifier = Modifier) {
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
                        renderEffect = RenderEffect
                            .createRuntimeShaderEffect(
                                PaperTexture,
                                "contents"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
            Slider()
            Spacer(modifier = Modifier.height(24.dp))
            Slider()
        }
    )
}

@Composable
fun NoiseGrain2Texture(modifier: Modifier = Modifier) {
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
                        renderEffect = RenderEffect
                            .createRuntimeShaderEffect(
                                NoiseGrain2,
                                "contents"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
            Slider()
            Spacer(modifier = Modifier.height(24.dp))
            Slider()
        }
    )
}

@Composable
fun NoiseGrain1Texture(modifier: Modifier = Modifier) {
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
                        renderEffect = RenderEffect
                            .createRuntimeShaderEffect(
                                NoiseGrain1,
                                "contents"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
            Slider()
            Spacer(modifier = Modifier.height(24.dp))
            Slider()
        }
    )
}

@Composable
fun RisographTexture(modifier: Modifier = Modifier) {
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
                        renderEffect = RenderEffect
                            .createRuntimeShaderEffect(
                                Risograph,
                                "contents"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
            Slider()
            Spacer(modifier = Modifier.height(24.dp))
            Slider()
        }
    )
}

@Composable
fun SketchingPaperTexture(modifier: Modifier = Modifier) {
    ShadyContainer(
        modifier = modifier,
        content = {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged { size ->
                        SketchingPaperTexture.setFloatUniform(
                            "resolution",
                            size.width.toFloat(),
                            size.height.toFloat()
                        )
                    }
                    .graphicsLayer {
                        renderEffect = RenderEffect
                            .createRuntimeShaderEffect(
                                SketchingPaperTexture,
                                "contents"
                            )
                            .asComposeRenderEffect()
                    },
                bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                contentDescription = null
            )
        },
        controls = {
            Slider()
            Spacer(modifier = Modifier.height(24.dp))
            Slider()
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
