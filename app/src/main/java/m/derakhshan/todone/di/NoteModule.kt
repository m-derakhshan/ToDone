package m.derakhshan.todone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import m.derakhshan.todone.core.data.data_source.MyDatabase
import m.derakhshan.todone.feature_notes.data.repository.NoteRepositoryImpl
import m.derakhshan.todone.feature_notes.domain.repository.NoteRepository
import m.derakhshan.todone.feature_notes.domain.use_case.*


@Module
@InstallIn(ViewModelComponent::class)
object NoteModule {


    @Provides
    @ViewModelScoped
    fun provideNoteRepository(db: MyDatabase): NoteRepository {
        return NoteRepositoryImpl(dao = db.noteDao)
    }

    @Provides
    @ViewModelScoped
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getNoteByID = GetNoteByID(repository = repository),
            insertNote = InsertNote(repository = repository),
            getNotes = GetNotes(repository = repository),
            deleteNote = DeleteNote(repository = repository)
        )
    }


}