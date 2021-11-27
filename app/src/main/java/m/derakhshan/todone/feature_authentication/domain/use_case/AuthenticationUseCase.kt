package m.derakhshan.todone.feature_authentication.domain.use_case



data class AuthenticationUseCase constructor(
    val login: Login,
    val signUp: SignUp
)