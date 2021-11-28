package m.derakhshan.todone.core.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import m.derakhshan.todone.core.Setting
import m.derakhshan.todone.feature_authentication.presentation.main.composable.AuthenticationMainScreen
import m.derakhshan.todone.feature_main.presentation.composable.MainScreen


@ExperimentalAnimationApi
@Composable
fun MainAppUI(setting: Setting) {

    val modifier = Modifier
    val navController = rememberNavController()

    Scaffold(modifier = modifier) {

        NavHost(
            navController = navController,
            startDestination =
            if (setting.isUserLoggedIn)
                NavGraph.MainScreen.route
            else
                NavGraph.AuthenticationScreen.route,
            modifier = Modifier.padding(it)
        ) {
            composable(NavGraph.AuthenticationScreen.route) {
                AuthenticationMainScreen(navController = navController)
            }
            composable(NavGraph.MainScreen.route) {
                MainScreen()
            }
        }
    }
}