package m.derakhshan.todone.feature_authentication.presentation.login

import m.derakhshan.todone.feature_authentication.domain.model.ServerResponse


data class LoginState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoginButtonExpanded: Boolean = true,
    val serverResponse: ServerResponse? = null
)
