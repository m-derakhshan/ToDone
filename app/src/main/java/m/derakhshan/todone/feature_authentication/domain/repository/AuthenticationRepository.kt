package m.derakhshan.todone.feature_authentication.domain.repository

import m.derakhshan.todone.feature_authentication.domain.model.LoginServerResponse
import m.derakhshan.todone.feature_authentication.domain.model.User


interface AuthenticationRepository {

    suspend fun insertUser(user: User)

    suspend fun login(username: String, password: String): LoginServerResponse
}

