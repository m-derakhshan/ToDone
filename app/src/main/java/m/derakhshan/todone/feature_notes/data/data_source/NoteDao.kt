package m.derakhshan.todone.feature_notes.data.data_source


import androidx.room.*
import kotlinx.coroutines.flow.Flow
import m.derakhshan.todone.feature_notes.domain.model.Notes


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Notes)

    @Delete
    suspend fun delete(note: Notes)

    @Query("SELECT * FROM Notes")
    fun getNotes(): Flow<List<Notes>>

    @Query("SELECT * FROM NOTES WHERE id=:id")
    suspend fun getNoteById(id: String): Notes?
}