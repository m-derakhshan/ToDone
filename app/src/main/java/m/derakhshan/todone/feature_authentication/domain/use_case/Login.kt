package m.derakhshan.todone.feature_authentication.domain.use_case


import m.derakhshan.todone.feature_authentication.domain.model.User
import m.derakhshan.todone.feature_authentication.domain.repository.AuthenticationRepository


class Login(
    private val repository: AuthenticationRepository
) {

    suspend operator fun invoke(username: String, password: String): User {
        return repository.login(username = username, password = password)
    }
}