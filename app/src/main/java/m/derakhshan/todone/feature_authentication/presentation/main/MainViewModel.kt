package m.derakhshan.todone.feature_authentication.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state


    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.SignUpButtonClick -> {
                _state.value = _state.value.copy(
                    signUpFormIsVisible = true
                )
            }
            is MainEvent.LoginButtonClick -> {
                _state.value = _state.value.copy(
                    loginFormIsVisible = true
                )
            }
            is MainEvent.BackButtonClick -> {
                _state.value = _state.value.copy(
                    loginFormIsVisible = false,
                    signUpFormIsVisible = false
                )
            }
        }
    }
}