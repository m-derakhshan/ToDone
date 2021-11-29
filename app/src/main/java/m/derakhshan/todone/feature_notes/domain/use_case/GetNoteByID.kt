package m.derakhshan.todone.feature_notes.domain.use_case

import m.derakhshan.todone.feature_notes.domain.model.Notes
import m.derakhshan.todone.feature_notes.domain.repository.NoteRepository

class GetNoteByID(private val repository: NoteRepository) {

    suspend operator fun invoke(id: String): Notes? {
        return repository.getNoteById(id = id)
    }
}