package m.derakhshan.todone.feature_authentication.presentation.sign_up

sealed class SignUpEvent(txt: String = "") {
    object SignUpClicked : SignUpEvent()
    object TogglePasswordVisibility : SignUpEvent()
    object DeleteSnackbar : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent(password)
    data class UsernameChanged(val username: String) : SignUpEvent(username)
    data class EmailChanged(val email: String) : SignUpEvent(email)
}
