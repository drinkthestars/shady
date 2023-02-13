package com.goofy.goober.androidtemplate.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun TemplateApp(
    templateData: String,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        TemplateContent(templateData)
    }
}

@Composable
private fun TemplateContent(
    templateData: String,
    modifier: Modifier = Modifier
) {
    var clickCount by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You clicked this image $clickCount time(s)")
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .wrapContentHeight()
                .clip(RoundedCornerShape(4.dp))
                .clickable { clickCount++ }
        ) {
            Image(
                contentScale = ContentScale.FillWidth,
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current).data(templateData)
                        .apply { crossfade(true) }.build()
                ),
                contentDescription = "Fluffy puppy"
            )
        }
    }
}
