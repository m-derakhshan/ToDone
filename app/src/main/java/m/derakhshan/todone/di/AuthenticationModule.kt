package m.derakhshan.todone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import m.derakhshan.todone.feature_authentication.data.data_source.MyDatabase
import m.derakhshan.todone.feature_authentication.data.repository.AuthenticationRepositoryImpl
import m.derakhshan.todone.feature_authentication.domain.repository.AuthenticationRepository


@Module
@InstallIn(ActivityComponent::class)
object AuthenticationModule {

    @Provides
    fun authenticationRepository(db: MyDatabase): AuthenticationRepository {
        return AuthenticationRepositoryImpl(userDao = db.userDao)
    }
}