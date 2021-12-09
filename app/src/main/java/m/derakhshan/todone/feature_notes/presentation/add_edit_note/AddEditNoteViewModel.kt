package m.derakhshan.todone.feature_notes.presentation.add_edit_note


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import m.derakhshan.todone.core.Setting
import m.derakhshan.todone.feature_notes.domain.model.Notes
import m.derakhshan.todone.feature_notes.domain.use_case.NoteUseCase
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val useCase: NoteUseCase,
    private val setting: Setting
) : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state


    var snackBarMsg = ""

    private var job: Job? = null

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.ColorSelected -> {
                _state.value = _state.value.copy(
                    background = event.color
                )
            }
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
                job?.cancel()
                job = viewModelScope.launch {
                    when (val validation = validateNote(event.note)) {
                        is NoteValidate.EmptyTitle -> snackBarMsg = validation.msg
                        is NoteValidate.EmptyContent -> snackBarMsg = validation.msg
                        is NoteValidate.NoError -> {

                            snackBarMsg = "Note Successfully Inserted!"

                            //--------------------(insert note into database)--------------------//
                            val note = event.note.copy(
                                id = setting.lastNoteAddedId,
                                timestamp = System.currentTimeMillis()
                            )
                            useCase.insertNote(note = note)
                            //--------------------(clear screen for new note )--------------------//
                            _state.value = _state.value.copy(
                                title = HintText(hint = "Enter title..."),
                                content = HintText(hint = "Enter some content..."),
                            )
                        }
                    }
                }

            }
            is NoteEvent.TitleFocusChanged -> {
                if (_state.value.title.text.isBlank())
                    _state.value = _state.value.copy(
                        title = HintText(hint = "Enter title...")
                    )
            }
            is NoteEvent.ContentFocusChanged -> {
                if (_state.value.content.text.isBlank())
                    _state.value = _state.value.copy(
                        content = HintText(hint = "Enter some content...")
                    )
            }
        }
    }


    private fun validateNote(note: Notes): NoteValidate {
        return when {
            note.title.isBlank() -> NoteValidate.EmptyTitle()
            note.content.isBlank() -> NoteValidate.EmptyContent()
            else -> NoteValidate.NoError()
        }
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}


private sealed class NoteValidate {
    data class EmptyTitle(val msg: String = "Title can't left blank!") : NoteValidate()
    data class NoError(val msg: String = "Note Is Ok!") : NoteValidate()
    data class EmptyContent(val msg: String = "Content can't left blank!") : NoteValidate()
}
