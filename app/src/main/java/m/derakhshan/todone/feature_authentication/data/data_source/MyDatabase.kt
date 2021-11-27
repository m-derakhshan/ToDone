package m.derakhshan.todone.feature_authentication.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import m.derakhshan.todone.feature_authentication.domain.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}