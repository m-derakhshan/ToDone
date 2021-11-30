package m.derakhshan.todone.feature_notes.presentation.add_edit_note

import androidx.compose.ui.graphics.Color
import m.derakhshan.todone.feature_notes.domain.model.Notes

data class NoteState(
    val background: Color = Notes.noteColors[0],
    val title: HintText = HintText(hint = "Enter title..."),
    val content: HintText = HintText(hint = "Enter some content..."),
)


data class HintText(
    val hint: String = "",
    val text: String = ""
)