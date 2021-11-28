package m.derakhshan.todone.feature_authentication.data.repository

import kotlinx.coroutines.delay
import m.derakhshan.todone.feature_authentication.data.data_source.UserDao
import m.derakhshan.todone.feature_authentication.domain.model.ServerResponse
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
    ): ServerResponse {
        if (username.isBlank()) {
            delay(500)
            return ServerResponse.Failed("Username can't left blank!")
        }

        if (password.isBlank()) {
            delay(500)
            return ServerResponse.Failed("Password can't left blank!")
        }

        delay(3000)
        userDao.insert(
            User(
                token = "What I get from Server",
                name = "What I get From server",
                username = username,
                email = "What I get From server",
                phone = "What I get From Server"
            )
        )
        return ServerResponse.Success("login was successful.")
    }

    override suspend fun signUp(username: String, password: String, email: String): ServerResponse {

        if (username.isBlank()) {
            delay(500)
            return ServerResponse.Failed("Username can't left blank!")
        }

        if (email.isBlank()) {
            delay(500)
            return ServerResponse.Failed("Email can't left blank!")
        }

        if (password.isBlank()) {
            delay(500)
            return ServerResponse.Failed("Password can't left blank!")
        }

        delay(3000)

        userDao.insert(
            User(
                token = "What I get from Server",
                name = "What I get From server",
                username = username,
                email = "What I get From server",
                phone = "What I get From Server"
            )
        )
        return ServerResponse.Success("user successfully registered.")
    }
}


