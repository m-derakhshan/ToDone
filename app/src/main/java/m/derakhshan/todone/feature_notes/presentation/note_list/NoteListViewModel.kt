package m.derakhshan.todone.feature_notes.presentation.note_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import m.derakhshan.todone.feature_notes.domain.use_case.NoteUseCase
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val useCase: NoteUseCase
):ViewModel(){



}