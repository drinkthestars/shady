package com.goofy.goober.shady.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

    Scaffold(
        topBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Space.Ten, horizontal = Space.Six),
                text = navController.currentDestination?.displayName.orEmpty(),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        modifier = modifier.fillMaxSize()
    ) { padding ->
        Content(
            home = home,
            screens = screens,
            modifier = modifier.padding(padding),
            navController = navController
        )
    }
}

@Composable
private fun Content(
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
            Box(
                modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.TopCenter
            ) {
                List(
                    screens = screens,
                    onClick = { screen -> navController.navigate(screen.title) }
                )
            }
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
            .padding(Space.Seven)
        ,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Spacer(Modifier.height(Space.Ten))
        }
        items(screens, key = { it.title }) { screen ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(screen) }
                    .padding(Space.Six),
                text = screen.title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge
            )
            Divider()
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
