package m.derakhshan.todone.feature_authentication.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m.derakhshan.todone.feature_authentication.domain.model.User
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
                viewModelScope.launch {
                    useCases.login(
                        username = _state.value.username,
                        password = _state.value.password
                    )
                }
            }
        }
    }
}