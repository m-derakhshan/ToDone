package m.derakhshan.todone.feature_authentication.domain.use_case


import m.derakhshan.todone.feature_authentication.domain.model.ServerResponse
import m.derakhshan.todone.feature_authentication.domain.repository.AuthenticationRepository


class Login (
    private val repository: AuthenticationRepository
) {

    suspend operator fun invoke(username: String, password: String): ServerResponse {
        return repository.login(username = username, password = password)
    }
}