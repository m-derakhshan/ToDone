package m.derakhshan.todone.feature_authentication.presentation.login


sealed class LoginEvent {
    object LoginClicked : LoginEvent()
}