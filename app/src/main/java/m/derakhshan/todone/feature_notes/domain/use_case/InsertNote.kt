package m.derakhshan.todone.feature_notes.domain.use_case

import m.derakhshan.todone.feature_notes.domain.model.Notes
import m.derakhshan.todone.feature_notes.domain.repository.NoteRepository

class InsertNote(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Notes) {
        repository.insert(note = note)
    }
}