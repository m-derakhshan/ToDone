package m.derakhshan.todone.feature_authentication.domain.model


sealed class LoginServerResponse(txt: String) {
    data class Success(val success: String = "") : LoginServerResponse(success)
    data class Failed(val error: String = "") : LoginServerResponse(error)
}