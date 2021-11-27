package m.derakhshan.todone.feature_authentication.domain.use_case

data class AuthenticationUseCase(
    val login: Login,
    val signUp: SignUp
)