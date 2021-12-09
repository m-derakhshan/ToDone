package m.derakhshan.todone.feature_notes.presentation.note_list.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
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

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun NoteListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NoteListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    val lazyListState = rememberLazyListState()
    val fabOffset by animateDpAsState(targetValue = viewModel.fabOffset.value,
        animationSpec = tween(durationMillis = 500))


    Scaffold(
        modifier = modifier,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NoteNavGraph.NoteScreen.route) },
                backgroundColor = Blue,
                modifier = Modifier.offset(y = fabOffset)
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

        //--------------------(hide or showing FAB after scrolling lazy column)--------------------//
        if (lazyListState.isScrollingUp())
            viewModel.onEvent(NoteListEvent.ScrollUp)
        else viewModel.onEvent(NoteListEvent.ScrollDown)


        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            state = lazyListState
        ) {
            items(state.notes) { note ->
                AnimatedVisibility(
                    visible = note.isVisible,
                    exit = fadeOut(tween(durationMillis = 300))
                ) {
                    Column {
                        Spacer(modifier = Modifier.padding(top = 16.dp))
                        NoteItem(note = note, modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                //--------------------(navigate to note page)--------------------//
                            }) {
                            viewModel.onEvent(NoteListEvent.DeleteClicked(note))
                            scope.launch {
                                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = deletedNoteMsg,
                                    actionLabel = "Undo",
                                )
                                if (result == SnackbarResult.ActionPerformed)
                                    viewModel.onEvent(NoteListEvent.RestoreNote)
                            }
                        }
                        Spacer(modifier = Modifier.padding(bottom = 8.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}