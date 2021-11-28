package m.derakhshan.todone.feature_authentication.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m.derakhshan.todone.feature_authentication.domain.use_case.AuthenticationUseCase
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCase: AuthenticationUseCase
) : ViewModel() {


    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

    fun onEvent(event: SignUpEvent) {

        when (event) {
            is SignUpEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email
                )
            }

            is SignUpEvent.UsernameChanged -> {
                _state.value = _state.value.copy(
                    username = event.username
                )
            }

            is SignUpEvent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password
                )
            }

            is SignUpEvent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(
                    isPasswordVisible = !_state.value.isPasswordVisible
                )
            }

            is SignUpEvent.SignUpClicked -> {
                signUp()
            }

            is SignUpEvent.DeleteSnackbar -> {
                _state.value = _state.value.copy(
                    serverResponse = null
                )
            }
        }
    }


    private fun signUp() {
        _state.value = _state.value.copy(
            isSignUpButtonExpanded = false
        )
        viewModelScope.launch {
            _state.value = _state.value.copy(
                serverResponse = useCase.signUp(
                    username = _state.value.username,
                    password = _state.value.password,
                    email = _state.value.email
                ),
                isSignUpButtonExpanded = true
            )
        }
    }
}