package m.derakhshan.todone.feature_authentication.presentation.sign_up

data class SignUpState(
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoginButtonExpanded: Boolean = true,
)
