package m.derakhshan.todone.feature_notes.presentation.note_list


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import m.derakhshan.todone.feature_notes.domain.model.Notes
import m.derakhshan.todone.feature_notes.domain.use_case.NoteUseCase
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val useCase: NoteUseCase
) : ViewModel() {

    private var noteJob: Job? = null


    var counter = 0

    private val _state = mutableStateOf(NoteListState())
    val state: State<NoteListState> = _state

    private var recentlyDeletedNote: Notes? = null

    init {
        getNotes()
    }


    fun onEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.NoteSelected -> {
                viewModelScope.launch {
                    useCase.getNoteByID(event.note.id)
                }
            }
            is NoteListEvent.AddNote -> {
                viewModelScope.launch {
                    val time = Calendar.getInstance().timeInMillis

                    useCase.insertNote(
                        Notes(
                            id = "$time",
                            title = "test",
                            content = "mohammad ${++counter}",
                            color = Notes.noteColors[(0..5).random()].toArgb(),
                            timestamp = time,
                            isVisible = true
                        )
                    )
                }
            }
            is NoteListEvent.DeleteClicked -> {
                viewModelScope.launch {

                    val newList = _state.value.notes.toMutableList()
                    newList[newList.indexOf(event.note)] = event.note.copy(
                        isVisible = false
                    )
                    recentlyDeletedNote = event.note
                    _state.value = _state.value.copy(
                        notes = newList
                    )
                    delay(300)
                    useCase.deleteNote(event.note)
                }
            }

            is NoteListEvent.RestoreNote -> {
                viewModelScope.launch {
                    viewModelScope.launch {
                        recentlyDeletedNote?.let { note ->
                            useCase.insertNote(note = note)
                        }
                        recentlyDeletedNote = null
                    }
                }
            }
        }
    }


    private fun getNotes() {
        noteJob?.cancel()
        noteJob = useCase.getNotes().onEach { notes ->
            _state.value = _state.value.copy(
                notes = notes
            )
        }.launchIn(viewModelScope)
    }

}