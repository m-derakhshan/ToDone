package m.derakhshan.todone.feature_notes.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import m.derakhshan.todone.feature_notes.domain.model.Notes
import m.derakhshan.todone.feature_notes.domain.repository.NoteRepository

class GetNotes(private val repository: NoteRepository) {

    operator fun invoke(): Flow<List<Notes>> {
        return repository.getNotes().map {notes->
            notes.sortedByDescending{ it.timestamp }
        }
    }
}