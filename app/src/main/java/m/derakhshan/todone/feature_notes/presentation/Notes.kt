package m.derakhshan.todone.feature_notes.presentation


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import m.derakhshan.todone.feature_notes.presentation.add_edit_note.AddEditNote
import m.derakhshan.todone.feature_notes.presentation.note_list.composable.NoteListScreen
import m.derakhshan.todone.feature_notes.presentation.note_list.composable.NoteNavGraph
@ExperimentalAnimationApi
@ExperimentalFoundationApi
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
                AddEditNote(navController = navController)
            }
        }

    }

}