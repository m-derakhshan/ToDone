package m.derakhshan.todone.feature_notes.domain.use_case


data class NoteUseCase(
    val editNote: EditNote,
    val insertNote: InsertNote,
    val getNotes: GetNotes,
    val deleteNote: DeleteNote
)
