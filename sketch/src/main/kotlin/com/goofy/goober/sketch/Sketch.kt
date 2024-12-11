package com.goofy.goober.sketch

import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache
import com.goofy.goober.style.Space

@Composable
fun produceDrawLoopCounter(speed: Float = 1f): State<Float> {
    return produceState(0f) {
        val firstFrame: Long = withFrameMillis { it }
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = (it - firstFrame) * speed / 1000f
            }
        }
    }
}

@Composable
fun SketchWithCache(
    modifier: Modifier = Modifier,
    speed: Float = 1f,
    onDrawWithCache: CacheDrawScope.(time: Float) -> DrawResult
) {
    val time by produceDrawLoopCounter(speed)
    Box(
        modifier = modifier.drawWithCache {
            onDrawWithCache(time)
        }
    )
}

// Unused
@Composable
private fun BoxScope.Controls(
    fps: State<Long>,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .align(Alignment.TopStart)
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
            .padding(Space.Three),
        text = "fps = ${fps.value}",
        color = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}
