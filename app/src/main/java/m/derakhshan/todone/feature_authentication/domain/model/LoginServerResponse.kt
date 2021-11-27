package m.derakhshan.todone.feature_authentication.domain.model


sealed class LoginServerResponse(msg: String) {

    class Success(msg: String = "") : LoginServerResponse(msg)
    class Failed(msg: String = "") : LoginServerResponse(msg)

}
