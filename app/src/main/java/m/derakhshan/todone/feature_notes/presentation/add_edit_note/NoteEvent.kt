package m.derakhshan.todone.feature_notes.presentation.add_edit_note

import androidx.compose.ui.graphics.Color
import m.derakhshan.todone.feature_notes.domain.model.Notes

sealed class NoteEvent {
    data class ColorSelected(val color: Color) : NoteEvent()
    data class TitleChanged(val title: String) : NoteEvent()
    data class ContentChanged(val content: String) : NoteEvent()
    data class SaveNote(val note: Notes) : NoteEvent()
}
