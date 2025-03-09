package com.goofy.goober.style

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CardDefaults.elevatedCardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val LargeCardShape = RoundedCornerShape(12.dp)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    title: String,
    subtitle: String? = null
) {
    ElevatedCard(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
            .border(1.dp, color = MaterialTheme.colorScheme.outlineVariant, shape = LargeCardShape),
        onClick = onClick,
        shape = LargeCardShape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .padding(start = 12.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = title
                )
                if (subtitle != null) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        text = subtitle
                    )
                }
            }
            Icon(
                modifier = Modifier.padding(end = 12.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    title: String
) {
    ElevatedCard(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
            .border(1.dp, color = MaterialTheme.colorScheme.outlineVariant, shape = LargeCardShape),
        elevation = elevatedCardElevation(4.dp),
        shape = LargeCardShape,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(Space.Five),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = title
            )
            Icon(
                modifier = Modifier.padding(end = Space.Five),
                imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun ShadyContainer(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
    controls: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
                .padding(Space.Five)
                .clip(LargeCardShape)
                .background(color = MaterialTheme.colorScheme.surfaceVariant),
            content = content
        )
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .padding(vertical = Space.Five)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.2f)
                .padding(horizontal = Space.Five),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Space.Five)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = controls
            )
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_MASK)
fun ShadyContainerPreview() {
    ShadyTheme {
        ShadyContainer(
            content = {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                )
            },
            controls = {
                Slider()
                Spacer(modifier = Modifier.height(24.dp))
                Slider()
            }
        )
    }
}

@Composable
fun Slider(
    modifier: Modifier = Modifier,
    label: String = "Slider",
    value: Float = 0.5f,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChange: (Float) -> Unit = {},
) {
    Column(modifier) {
        Text(
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            text = label
        )
        Spacer(Modifier.height(4.dp))
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_MASK)
fun SmallCardColumnPreview() {
    ShadyTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.size(0.dp, 12.dp))
            SmallCard(title = "Title 1")
            Spacer(Modifier.size(0.dp, 12.dp))
            SmallCard(title = "Title 2")
            Spacer(Modifier.size(0.dp, 12.dp))
            SmallCard(title = "Title 3")
            Spacer(Modifier.size(0.dp, 12.dp))
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_MASK)
fun LargeCardColumnPreview() {
    ShadyTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.size(0.dp, 12.dp))
            LargeCard(Modifier.size(360.dp, 100.dp), title = "Title 1")
            Spacer(Modifier.size(0.dp, 12.dp))
            LargeCard(Modifier.size(360.dp, 100.dp), title = "Title 2")
            Spacer(Modifier.size(0.dp, 12.dp))
            LargeCard(Modifier.size(360.dp, 100.dp), title = "Title 3")
            Spacer(Modifier.size(0.dp, 12.dp))
        }
    }
}
