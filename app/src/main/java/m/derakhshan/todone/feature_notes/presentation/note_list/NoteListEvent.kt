package m.derakhshan.todone.feature_notes.presentation.note_list

import m.derakhshan.todone.feature_notes.domain.model.Notes

sealed class NoteListEvent(note: Notes? = null) {

    object AddNote : NoteListEvent()
    object RestoreNote : NoteListEvent()
    data class NoteSelected(val note: Notes) : NoteListEvent(note = note)
    data class DeleteClicked(val note: Notes) : NoteListEvent(note = note)

}
