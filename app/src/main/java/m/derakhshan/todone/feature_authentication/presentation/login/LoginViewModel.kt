package m.derakhshan.todone.feature_authentication.presentation.login


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m.derakhshan.todone.feature_authentication.domain.model.ServerResponse
import m.derakhshan.todone.feature_authentication.domain.use_case.AuthenticationUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: AuthenticationUseCase
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state


    fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.LoginClicked -> {
                doLogin()
            }

            is LoginEvent.ChangeUsername -> {
                _state.value = _state.value.copy(
                    username = event.username
                )
            }

            is LoginEvent.ChangePassword -> {
                _state.value = _state.value.copy(
                    password = event.password
                )
            }

            is LoginEvent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(
                    isPasswordVisible = !_state.value.isPasswordVisible
                )
            }

            is LoginEvent.ForgetPassClicked -> {

            }

            is LoginEvent.DeleteSnackbar->{
                _state.value = _state.value.copy(
                    snackbarMsg = ""
                )
            }
        }
    }


    private fun doLogin() {

        _state.value = _state.value.copy(
            isLoginButtonExpanded = false
        )

        viewModelScope.launch {
            val response: ServerResponse = useCases.login(
                username = _state.value.username,
                password = _state.value.password
            )
            when (response) {
                is ServerResponse.Success -> {
                    _state.value = _state.value.copy(
                        isLoginButtonExpanded = true,
                        enterApp = true,
                        snackbarMsg = response.success
                    )
                }
                is ServerResponse.Failed -> {
                    _state.value = _state.value.copy(
                        isLoginButtonExpanded = true,
                        snackbarMsg = response.error
                    )
                }
            }
        }
    }
}