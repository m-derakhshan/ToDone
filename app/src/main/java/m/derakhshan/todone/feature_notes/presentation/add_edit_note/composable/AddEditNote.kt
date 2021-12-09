package m.derakhshan.todone.feature_notes.presentation.add_edit_note.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import m.derakhshan.todone.core.presentation.BackSwipeGesture
import m.derakhshan.todone.feature_notes.domain.model.Notes
import m.derakhshan.todone.feature_notes.presentation.add_edit_note.AddEditNoteViewModel
import m.derakhshan.todone.feature_notes.presentation.add_edit_note.NoteEvent
import m.derakhshan.todone.ui.theme.Black
import m.derakhshan.todone.ui.theme.White

@Composable
fun AddEditNote(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {


    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    var offset by remember { mutableStateOf(0f) }

    val state = viewModel.state.value
    val colorItemHorizontalScrollString = rememberScrollState()

    Scaffold(
        modifier = modifier.draggable(
            orientation = Orientation.Horizontal,
            state = rememberDraggableState { delta ->
                offset += (delta * 0.2f)
            }, onDragStopped = {
                if (offset > 90)
                    navController.navigateUp()
                offset = 0f
            }
        ),
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(
                    NoteEvent.SaveNote(
                        Notes(
                            title = state.title.text,
                            content = state.content.text,
                            color = state.background.toArgb(),
                        )
                    )
                )

                scope.launch {
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = viewModel.snackBarMsg,
                        duration = SnackbarDuration.Short
                    )
                }
            }, backgroundColor = Notes.getOriginalColor(state.background)) {
                Icon(
                    imageVector = Icons.Filled.NoteAdd, contentDescription = "Save",
                    tint = White
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(state.background)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(colorItemHorizontalScrollString)
            ) {
                for (colorItem in Notes.noteColors)
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(50.dp)
                            .shadow(15.dp, shape = CircleShape)
                            .background(color = colorItem)
                            .clip(CircleShape)
                            .border(
                                width = 3.dp,
                                color = Color(
                                    ColorUtils.blendARGB(
                                        colorItem.toArgb(),
                                        Black.toArgb(),
                                        0.2f
                                    )
                                ),
                                shape = CircleShape
                            )
                            .clickable {
                                viewModel.onEvent(NoteEvent.ColorSelected(color = colorItem))
                            }
                    )
            }

            HintTextField(
                modifier = Modifier.padding(16.dp),
                text = state.title.text,
                hint = state.title.hint,
                onValueChange = { viewModel.onEvent(NoteEvent.TitleChanged(it)) },
                textStyle = MaterialTheme.typography.h6,
                onFocusChange = {}
            )

            HintTextField(
                modifier = Modifier.padding(16.dp),
                text = state.content.text,
                hint = state.content.hint,
                onValueChange = { viewModel.onEvent(NoteEvent.ContentChanged(it)) },
                textStyle = MaterialTheme.typography.body1,
                onFocusChange = {}
            )
        }

        BackSwipeGesture(offset = offset, arcColor = Notes.getLightColor(state.background))
    }

}