package m.derakhshan.todone.feature_authentication.presentation.login


data class LoginState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoginButtonExpanded: Boolean = true,
    val snackbarMsg: String = "",
    val enterApp: Boolean = false
)
