package m.derakhshan.todone.feature_authentication.domain.model


sealed class ServerResponse(txt: String) {
    data class Success(val success: String = "") : ServerResponse(success)
    data class Failed(val error: String = "") : ServerResponse(error)
}