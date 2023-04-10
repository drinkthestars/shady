package com.goofy.goober.shady.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.goofy.goober.style.LargeCard
import com.goofy.goober.style.ShadyTheme
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
    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        style = MaterialTheme.typography.headlineSmall,
                        text = currentRoute.value?.destination?.route ?: home,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
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
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = home
    ) {
        composable(home) {
            List(
                screens = screens,
                onClick = { screen ->
                    navController.navigate(screen.title)
                }
            )
        }
        screens.forEach { screen ->
            navigation(
                route = "${screen.title} Home",
                startDestination = screen.title
            ) {
                if (screen is NestedNavScreen) {
                    screen.nestedGraph(this) {
                        navController.navigate(it.title)
                    }
                }
            }
        }
    }
}

@Composable
fun List(
    screens: List<Screen>,
    onClick: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .wrapContentHeight()
            .padding(Space.Three),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Spacer(Modifier.height(Space.Four))
        }
        items(screens, key = { it.title }) { screen ->
            LargeCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Space.Five),
                title = screen.title,
                subtitle = screen.description,
                onClick = { onClick(screen) }
            )
            Spacer(Modifier.height(Space.Three))
        }
        item {
            Spacer(Modifier.height(Space.Twelve))
        }
    }
}

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
