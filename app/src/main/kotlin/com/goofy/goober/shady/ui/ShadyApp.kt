package com.goofy.goober.shady.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goofy.goober.style.LargeCard
import com.goofy.goober.style.ShadyTheme
import com.goofy.goober.style.SmallCard
import com.goofy.goober.style.Space
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ShadyApp(modifier: Modifier = Modifier) {
    ThemedSystemBarIconsEffect()
    ShadyTheme {
        Scaffold(modifier, home = HomeScreens.Home, screens = TopLevelScreens)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Scaffold(
    modifier: Modifier = Modifier,
    home: String,
    screens: List<Screen>
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        navController.currentDestination?.displayName ?: "Shady",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.ChevronLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { padding ->
        Content(
            home = home,
            screens = screens,
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}

@Composable
fun Content(
    home: String,
    screens: List<Screen>,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = home
    ) {
        composable(home) {
            List(
                screens = screens,
                onClick = { screen -> navController.navigate(screen.title) }
            )
        }
        screens.forEach { screen ->
            composable(screen.title) { screen.content() }
        }
    }
}

@Composable
private fun List(
    screens: List<Screen>,
    onClick: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .wrapContentHeight()
            .padding(Space.Seven),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        items(screens, key = { it.title }) { screen ->
            LargeCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Space.Six),
                title = screen.title,
                onClick = { onClick(screen) }
            )
            Spacer(Modifier.height(Space.Four))
        }
        item {
            Spacer(Modifier.height(Space.Twelve))
        }
    }
}

data class Screen(val title: String, val content: @Composable () -> Unit)

@Composable
private fun ThemedSystemBarIconsEffect() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = isSystemInDarkTheme().not()

    DisposableEffect(Unit) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        onDispose { }
    }
}
