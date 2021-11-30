package m.derakhshan.todone.feature_notes.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import m.derakhshan.todone.feature_notes.domain.use_case.NoteUseCase
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val useCase: NoteUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private val _snackBarMsg = MutableSharedFlow<String>()
    val snackBarMsg: SharedFlow<String> = _snackBarMsg

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.ColorSelected ->
                _state.value = _state.value.copy(
                    background = event.color
                )
            is NoteEvent.TitleChanged -> {
                _state.value = _state.value.copy(
                    title = HintText(text = event.title)
                )
            }
            is NoteEvent.ContentChanged -> {
                _state.value = _state.value.copy(
                    content = HintText(text = event.content)
                )
            }

            is NoteEvent.SaveNote -> {
                viewModelScope.launch {
                    useCase.insertNote(note = event.note)
                    _snackBarMsg.emit("Note Successfully Inserted!")
                }
            }
        }
    }

}