package com.goofy.goober.sketch

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.isActive

@Composable
fun produceDrawLoopCounter(speed: Float = 1f): State<Float> {
    return produceState(0f) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it / 1000f * speed
            }
        }
    }
}

@Composable
fun SketchWithCache(
    modifier: Modifier = Modifier,
    speed: Float = 1f,
    showControls: Boolean = false,
    animationSpec: AnimationSpec<Float> = tween(5000, 50, easing = LinearEasing),
    onDrawWithCache: CacheDrawScope.(Float) -> DrawResult
) {
    val fps = fps()
    var size by remember { mutableStateOf(IntSize.Zero) }
    var boundsInWindow by remember { mutableStateOf(Rect.Zero) }
    val advance = remember { AnimationState(0f) }

    LaunchedEffect(Unit) {
        while (isActive) {
            advance.animateTo(
                targetValue = advance.value + speed,
                animationSpec = animationSpec,
                sequentialAnimation = true
            )
        }
    }

    if (showControls) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .onGloballyPositioned {
                    size = it.size
                    boundsInWindow = it.boundsInWindow()
                }
                .drawWithCache {
                    onDrawWithCache(advance.value)
                }
            )
            Controls(fps)
        }
    } else {
        Box(
            modifier = modifier.drawWithCache {
                onDrawWithCache(advance.value)
            }
        )
    }
}

@Composable
fun Sketch(
    modifier: Modifier = Modifier,
    speed: Float = 1f,
    showControls: Boolean = false,
    animationSpec: AnimationSpec<Float> = tween(5000, 50, easing = LinearEasing),
    onDraw: DrawScope.(Float) -> Unit
) {
    val fps = fps()
    var size by remember { mutableStateOf(IntSize.Zero) }
    var boundsInWindow by remember { mutableStateOf(Rect.Zero) }
    val advance = remember { AnimationState(0f) }

    LaunchedEffect(Unit) {
        while (isActive) {
            advance.animateTo(
                targetValue = advance.value + speed,
                animationSpec = animationSpec,
                sequentialAnimation = true
            )
        }
    }

    if (showControls) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Canvas(
                modifier = modifier
                    .onGloballyPositioned {
                        size = it.size
                        boundsInWindow = it.boundsInWindow()
                    },
            ) {
                onDraw(advance.value)
            }
            Controls(fps)
        }
    } else {
        Canvas(modifier = modifier) {
            onDraw(advance.value)
        }
    }
}

@Composable
private fun Controls(
    fps: State<Long>,
    content: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        content()
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "fps = ${fps.value}",
            color = Color.Black,
        )
    }
}
