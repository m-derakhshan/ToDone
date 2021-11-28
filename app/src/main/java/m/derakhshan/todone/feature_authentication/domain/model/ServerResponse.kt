package m.derakhshan.todone.feature_authentication.domain.model


sealed class ServerResponse(txt: String, code: Int?) {
    data class Success(
        val success: String = "",
        val code: Int? = null
    ) : ServerResponse(success, code = code)

    data class Failed(
        val error: String = "",
        val code: Int? = null
    ) : ServerResponse(error, code = code)
}