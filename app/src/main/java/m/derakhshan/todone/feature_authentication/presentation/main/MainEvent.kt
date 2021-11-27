package m.derakhshan.todone.feature_authentication.presentation.main

sealed class MainEvent{
    object LoginButtonClick : MainEvent()
    object SignUpButtonClick : MainEvent()
    object BackButtonClick : MainEvent()
}
