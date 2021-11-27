package m.derakhshan.todone.feature_authentication.domain.repository

import m.derakhshan.todone.feature_authentication.domain.model.User


interface AuthenticationRepository {

    suspend fun insertUser(user: User)
}