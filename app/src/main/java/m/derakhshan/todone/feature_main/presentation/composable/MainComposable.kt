package m.derakhshan.todone.feature_main.presentation.composable


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import m.derakhshan.todone.feature_home.presentation.composable.Home
import m.derakhshan.todone.feature_main.presentation.MainNavGraph
import m.derakhshan.todone.feature_notes.presentation.Notes
import m.derakhshan.todone.feature_profile.presentation.composable.Profile

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(
        MainNavGraph.Home,
        MainNavGraph.TaskList,
        MainNavGraph.NoteList,
        MainNavGraph.Profile
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = screen.route) },
                        label = { Text(stringResource(screen.resourceID)) },
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
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = MainNavGraph.Home.route,
        ) {
            composable(MainNavGraph.Home.route) {
                Home()
            }
            composable(MainNavGraph.TaskList.route) {
            }
            composable(MainNavGraph.NoteList.route) {
                Notes(Modifier.padding(padding))
            }
            composable(MainNavGraph.Profile.route) {
                Profile(Modifier.padding(padding))
            }
        }
    }
}