package m.derakhshan.todone.feature_notes.domain.repository


import kotlinx.coroutines.flow.Flow
import m.derakhshan.todone.feature_notes.domain.model.Notes

interface NoteRepository {

    suspend fun insert(note: Notes)

    suspend fun delete(note: Notes)

    fun getNotes(): Flow<List<Notes>>

    suspend fun getNoteById(id: Int): Notes?
}