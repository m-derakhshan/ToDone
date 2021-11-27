package m.derakhshan.todone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import m.derakhshan.todone.feature_authentication.data.data_source.MyDatabase
import m.derakhshan.todone.feature_authentication.data.repository.AuthenticationRepositoryImpl
import m.derakhshan.todone.feature_authentication.domain.repository.AuthenticationRepository
import m.derakhshan.todone.feature_authentication.domain.use_case.AuthenticationUseCase
import m.derakhshan.todone.feature_authentication.domain.use_case.Login
import m.derakhshan.todone.feature_authentication.domain.use_case.SignUp


@Module
@InstallIn(ViewModelComponent::class)
object AuthenticationModule {

    @Provides
    fun authenticationRepository(db: MyDatabase): AuthenticationRepository {
        return AuthenticationRepositoryImpl(userDao = db.userDao)
    }

    @Provides
    @ViewModelScoped
    fun provideAuthenticationUseCase(repository: AuthenticationRepository): AuthenticationUseCase {
        return AuthenticationUseCase(
            login = Login(repository = repository),
            signUp = SignUp(repository = repository)
        )
    }
}