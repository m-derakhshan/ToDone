package m.derakhshan.todone.feature_authentication.presentation.login


sealed class LoginEvent(txt: String?) {
    object LoginClicked : LoginEvent(null)
    object TogglePasswordVisibility : LoginEvent(null)
    data class ChangeUsername(val username: String) : LoginEvent(username)
    data class ChangePassword(val password: String) : LoginEvent(password)
}