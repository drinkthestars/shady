package com.goofy.goober.shady.static

import android.graphics.RenderEffect
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.imageResource
import com.goofy.goober.shaders.PaperTexture
import com.goofy.goober.shady.R

@Composable
fun PaperTextureContent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(0.7f)
                .background(color = MaterialTheme.colorScheme.secondaryContainer) // Color(0xff262623)
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
    }
}
