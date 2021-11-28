package m.derakhshan.todone.feature_authentication.domain.repository

import m.derakhshan.todone.feature_authentication.domain.model.ServerResponse
import m.derakhshan.todone.feature_authentication.domain.model.User


interface AuthenticationRepository {

    suspend fun insertUser(user: User)

    suspend fun login(username: String, password: String): ServerResponse

    suspend fun signUp(username: String, password: String, email: String): ServerResponse

}

