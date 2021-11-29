package m.derakhshan.todone.feature_notes.presentation.note_list.composable

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import m.derakhshan.todone.R
import m.derakhshan.todone.feature_notes.presentation.note_list.NoteListEvent
import m.derakhshan.todone.feature_notes.presentation.note_list.NoteListViewModel
import m.derakhshan.todone.ui.theme.Blue
import m.derakhshan.todone.ui.theme.White

@Composable
fun NoteListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NoteListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(NoteListEvent.AddNote) },
                backgroundColor = Blue
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add",
                    tint = White
                )
            }
        },
        scaffoldState = scaffoldState
    )
    {
        val deletedNoteMsg = stringResource(id = R.string.note_deleted)
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(state.notes) { note ->
                NoteItem(note = note, modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        //--------------------(navigate to note page)--------------------//
                    }) {
                    viewModel.onEvent(NoteListEvent.DeleteClicked(note))
                    scope.launch {
                        val result = scaffoldState.snackbarHostState.showSnackbar(
                            message = deletedNoteMsg,
                            actionLabel = "Undo",
                        )
                        if (result == SnackbarResult.ActionPerformed)
                            viewModel.onEvent(NoteListEvent.RestoreNote)
                    }
                }
            }
        }
    }

}