package m.derakhshan.todone.feature_authentication.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m.derakhshan.todone.feature_authentication.domain.model.LoginServerResponse
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
        }
    }


    private fun doLogin() {

        _state.value = _state.value.copy(
            isLoginButtonExpanded = false
        )

        viewModelScope.launch {
            val loginServerResponse: LoginServerResponse = useCases.login(
                username = _state.value.username,
                password = _state.value.password
            )
            when (loginServerResponse) {
                is LoginServerResponse.Success -> {
                    _state.value = _state.value.copy(
                        isLoginButtonExpanded = true
                    )
                    Log.i("Log", "doLogin: snackbar txt is ${loginServerResponse.success}")
                }
                is LoginServerResponse.Failed -> {
                    _state.value = _state.value.copy(
                        isLoginButtonExpanded = true
                    )
                    Log.i("Log", "doLogin: snackbar txt is ${loginServerResponse.error}")
                }
            }
        }
    }
}