package m.derakhshan.todone.feature_main.presentation.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Task
import androidx.compose.ui.graphics.vector.ImageVector
import m.derakhshan.todone.R

sealed class MainNavGraph(
    val route: String,
    val resourceID: Int,
    val icon: ImageVector
) {
    object Home : MainNavGraph(
        route = "Home",
        resourceID = R.string.home,
        icon = Icons.Filled.Home
    )

    object TaskList : MainNavGraph(
        route = "TaskList",
        resourceID = R.string.task_list,
        icon = Icons.Filled.Task
    )

    object NoteList : MainNavGraph(
        route = "NoteList",
        resourceID = R.string.note_list,
        icon = Icons.Filled.Notes
    )

    object Profile : MainNavGraph(
        route = "Profile",
        resourceID = R.string.profile,
        icon = Icons.Filled.Person
    )

}