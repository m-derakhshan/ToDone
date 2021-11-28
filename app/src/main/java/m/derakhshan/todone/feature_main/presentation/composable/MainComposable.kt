package m.derakhshan.todone.feature_main.presentation.composable


import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import m.derakhshan.todone.core.presentation.NavGraph
import m.derakhshan.todone.feature_authentication.presentation.main.composable.AuthenticationMainScreen
import m.derakhshan.todone.feature_notes.presentation.note_list.NoteList


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold {

        NavHost(
            navController = navController,
            startDestination = MainNavGraph.Home.route,
            modifier = Modifier.padding(it)
        ) {
            composable(MainNavGraph.Home.route) {
                NoteList(navController = navController)
            }
            composable(MainNavGraph.Home.route) {
                NoteList(navController = navController)
            }
        }
    }
}