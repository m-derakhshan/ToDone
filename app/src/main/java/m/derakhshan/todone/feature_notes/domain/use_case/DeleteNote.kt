package m.derakhshan.todone.feature_notes.domain.use_case

import m.derakhshan.todone.feature_notes.domain.model.Notes
import m.derakhshan.todone.feature_notes.domain.repository.NoteRepository

class DeleteNote(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Notes) {
        repository.delete(note)
    }
}