package m.derakhshan.todone.feature_notes.presentation.note_list.composable


import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Notes(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = NoteNavGraph.NoteListScreen.route
        ) {

            composable(route = NoteNavGraph.NoteListScreen.route) {
                NoteListScreen(modifier = modifier, navController = navController)
            }
            composable(route = NoteNavGraph.NoteScreen.route) {
                NoteScreen(navController = navController)
            }
        }

    }

}