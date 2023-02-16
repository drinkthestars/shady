package com.goofy.goober.sketch

import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameMillis

@Composable
fun fps(): State<Long> {
    val fps = remember { mutableStateOf(0L) }
    LaunchedEffect(Unit) {
        var prevTime = withInfiniteAnimationFrameMillis { it }
        while (true) {
            withFrameMillis { frameTime ->
                fps.value = 1000 / (frameTime - prevTime).coerceAtLeast(1L)
                prevTime = frameTime
            }
        }
    }
    return fps
}
