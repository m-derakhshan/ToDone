package m.derakhshan.todone.feature_notes.domain.use_case


data class NoteUseCase(
    val getNoteByID: GetNoteByID,
    val insertNote: InsertNote,
    val getNotes: GetNotes,
    val deleteNote: DeleteNote
)
