package m.derakhshan.todone.feature_authentication.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import m.derakhshan.todone.feature_authentication.domain.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("DELETE FROM  User")
    suspend fun delete()

    @Query("SELECT * FROM User WHERE username=:username")
     fun getUserByUsername(username: String): Flow<User>

}