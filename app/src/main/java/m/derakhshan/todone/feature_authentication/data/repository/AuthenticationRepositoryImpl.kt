package m.derakhshan.todone.feature_authentication.data.repository

import kotlinx.coroutines.delay
import m.derakhshan.todone.feature_authentication.data.data_source.UserDao
import m.derakhshan.todone.feature_authentication.domain.model.LoginServerResponse
import m.derakhshan.todone.feature_authentication.domain.model.User
import m.derakhshan.todone.feature_authentication.domain.repository.AuthenticationRepository


class AuthenticationRepositoryImpl(
    private val userDao: UserDao
) : AuthenticationRepository {

    override suspend fun insertUser(user: User) {
        userDao.insert(user = user)
    }

    override suspend fun login(
        username: String, password: String,
    ): LoginServerResponse {
        delay(5000)
        userDao.insert(
            User(
                token = "What I get from Server",
                name = "What I get From server",
                username = username,
                email = "What I get From server",
                phone = "What I get From Server"
            )
        )
        return LoginServerResponse.Success("done")
    }
}


