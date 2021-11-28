package m.derakhshan.todone.feature_authentication.presentation.login


sealed class LoginEvent(txt: String = "") {
    object LoginClicked : LoginEvent()
    object TogglePasswordVisibility : LoginEvent()
    object ForgetPassClicked : LoginEvent()
    object DeleteSnackbar : LoginEvent()
    data class ChangeUsername(val username: String) : LoginEvent(username)
    data class ChangePassword(val password: String) : LoginEvent(password)
}