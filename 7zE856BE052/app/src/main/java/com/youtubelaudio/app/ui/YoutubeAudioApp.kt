package com.youtubelaudio.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.youtubelaudio.app.ui.screen.DownloadsScreen
import com.youtubelaudio.app.ui.screen.EditorScreen
import com.youtubelaudio.app.ui.screen.HistoryScreen
import com.youtubelaudio.app.ui.screen.PlayerScreen
import com.youtubelaudio.app.ui.screen.SearchScreen

sealed class Screen(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    data object Search : Screen("search", "Поиск", Icons.Filled.Search)
    data object Downloads : Screen("downloads", "Загрузки", Icons.Filled.Download)
    data object History : Screen("history", "История", Icons.Filled.History)
    data object Library : Screen("library", "Библиотека", Icons.Filled.LibraryMusic)
    data object Player : Screen("player", "Плеер", Icons.Filled.LibraryMusic)
    data object Editor : Screen("editor/{path}", "Редактор", Icons.Filled.LibraryMusic)
}

@Composable
fun YoutubeAudioApp() {
    val navController = rememberNavController()
    val items = listOf(Screen.Search, Screen.Downloads, Screen.History, Screen.Library)
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Screen.Search.route, modifier = Modifier.padding(innerPadding)) {
            composable(Screen.Search.route) { SearchScreen(navController) }
            composable(Screen.Downloads.route) { DownloadsScreen(navController) }
            composable(Screen.History.route) { HistoryScreen(navController) }
            composable(Screen.Library.route) { LibraryScreen(navController) }
            composable(Screen.Player.route) { PlayerScreen(navController) }
            composable(Screen.Editor.route) { backStackEntry ->
                val path = backStackEntry.arguments?.getString("path").orEmpty()
                EditorScreen(navController, path)
            }
        }
    }
}
