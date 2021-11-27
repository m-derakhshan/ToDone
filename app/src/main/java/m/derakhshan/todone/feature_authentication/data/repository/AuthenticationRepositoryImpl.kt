package m.derakhshan.todone.feature_authentication.data.repository

import m.derakhshan.todone.feature_authentication.data.data_source.UserDao
import m.derakhshan.todone.feature_authentication.domain.model.User
import m.derakhshan.todone.feature_authentication.domain.repository.AuthenticationRepository


class AuthenticationRepositoryImpl(
    private val userDao: UserDao
) : AuthenticationRepository {

    override suspend fun insertUser(user: User) {
        return userDao.insert(user = user)
    }
}