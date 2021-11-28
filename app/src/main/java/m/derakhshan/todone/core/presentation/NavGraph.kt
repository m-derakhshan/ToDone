package m.derakhshan.todone.core.presentation

import androidx.compose.material.icons.Icons

sealed class NavGraph(val route: String, val resourceID: String? = null, val icon: Icons? = null) {
    object AuthenticationScreen : NavGraph(route = "Authentication")
    object MainScreen : NavGraph(route = "Main")
}
