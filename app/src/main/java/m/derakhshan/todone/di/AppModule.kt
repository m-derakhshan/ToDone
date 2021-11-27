package m.derakhshan.todone.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import m.derakhshan.todone.feature_authentication.data.data_source.MyDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RoomDatabase {
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "my_db"
        ).build()
    }
}