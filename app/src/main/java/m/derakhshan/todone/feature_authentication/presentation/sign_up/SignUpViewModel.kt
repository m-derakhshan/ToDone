package m.derakhshan.todone.feature_authentication.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
                    email = event.username
                )
            }

            is SignUpEvent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    email = event.password
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
        }
    }


    private fun signUp() {

    }
}