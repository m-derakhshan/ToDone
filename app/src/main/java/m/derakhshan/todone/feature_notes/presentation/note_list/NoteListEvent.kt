package m.derakhshan.todone.feature_notes.presentation.note_list

import m.derakhshan.todone.feature_notes.domain.model.Notes

sealed class NoteListEvent{
    object RestoreNote : NoteListEvent()
    object ScrollUp : NoteListEvent()
    object ScrollDown : NoteListEvent()
    data class NoteSelected(val note: Notes) : NoteListEvent()
    data class DeleteClicked(val note: Notes) : NoteListEvent()
}
