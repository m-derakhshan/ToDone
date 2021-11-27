package m.derakhshan.todone.feature_authentication.domain.use_case


import m.derakhshan.todone.feature_authentication.domain.model.LoginServerResponse
import m.derakhshan.todone.feature_authentication.domain.model.User
import m.derakhshan.todone.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject


class Login (
    private val repository: AuthenticationRepository
) {

    suspend operator fun invoke(username: String, password: String): LoginServerResponse {
        return repository.login(username = username, password = password)
    }
}