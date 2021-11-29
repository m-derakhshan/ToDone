package m.derakhshan.todone.feature_notes.presentation.note_list


import m.derakhshan.todone.feature_notes.domain.model.Notes

data class NoteListState(
    val notes: List<Notes> = emptyList(),
)
