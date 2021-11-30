package m.derakhshan.todone.feature_notes.data.repository

import kotlinx.coroutines.flow.Flow
import m.derakhshan.todone.feature_notes.data.data_source.NoteDao
import m.derakhshan.todone.feature_notes.domain.model.Notes
import m.derakhshan.todone.feature_notes.domain.repository.NoteRepository

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {

    override suspend fun insert(note: Notes) {
        dao.insert(note = note)
    }

    override suspend fun delete(note:Notes) {
        dao.delete(note)
    }

    override fun getNotes(): Flow<List<Notes>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: String): Notes? {
        return dao.getNoteById(id = id)
    }
}