package m.derakhshan.todone.feature_authentication.domain.use_case

import m.derakhshan.todone.feature_authentication.domain.model.ServerResponse
import m.derakhshan.todone.feature_authentication.domain.repository.AuthenticationRepository


class SignUp(private val repository: AuthenticationRepository) {

    suspend operator fun invoke(username: String, password: String, email: String): ServerResponse {
        return repository.signUp(username = username, password = password, email = email)
    }
}