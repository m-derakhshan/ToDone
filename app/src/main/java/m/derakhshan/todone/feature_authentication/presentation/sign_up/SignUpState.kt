package m.derakhshan.todone.feature_authentication.presentation.sign_up

import m.derakhshan.todone.feature_authentication.domain.model.ServerResponse

data class SignUpState(
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val isPasswordVisible: Boolean = false,
    val isSignUpButtonExpanded: Boolean = true,
    val serverResponse: ServerResponse? = null
)
