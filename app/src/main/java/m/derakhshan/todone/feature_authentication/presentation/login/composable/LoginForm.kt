package m.derakhshan.todone.feature_authentication.presentation.login.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import m.derakhshan.todone.R
import m.derakhshan.todone.feature_authentication.domain.model.ServerResponse
import m.derakhshan.todone.feature_authentication.presentation.login.LoginEvent
import m.derakhshan.todone.feature_authentication.presentation.login.LoginViewModel


@ExperimentalAnimationApi
@Composable
fun LoginForm(show: Boolean, snackBarMsg: (String, Boolean) -> Unit) {

    val viewModel: LoginViewModel = hiltViewModel()
    val state = viewModel.state.value

    state.serverResponse?.let { response ->
        if (response is ServerResponse.Success)
            snackBarMsg(response.success, response.code == 200).also {
                viewModel.onEvent(LoginEvent.DeleteSnackbar)
            }
        else if (response is ServerResponse.Failed)
            snackBarMsg(response.error, response.code == 200).also {
                viewModel.onEvent(LoginEvent.DeleteSnackbar)
            }
    }

    AnimatedVisibility(
        visible = show,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 1000),
            initialOffsetY = { -it }),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 500),
            targetOffsetY = { -it })
    ) {

        Column(
            modifier = Modifier.padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = stringResource(id = R.string.welcomeBack),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                value = state.username,
                onValueChange = { viewModel.onEvent(LoginEvent.ChangeUsername(it)) },
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.username
                        )
                    )
                })

            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(LoginEvent.ChangePassword(it)) },
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.password
                        )
                    )
                },
                visualTransformation =
                if (state.isPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                maxLines = 1,
                trailingIcon = {
                    val image = if (state.isPasswordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(onClick = {
                        viewModel.onEvent(LoginEvent.TogglePasswordVisibility)
                    }) {
                        Icon(imageVector = image, "show or hide password")
                    }
                }
            )

            LoadingButton(text = R.string.login, expand = state.isLoginButtonExpanded) {
                viewModel.onEvent(LoginEvent.LoginClicked)
            }


            val forgetPass = buildAnnotatedString {
                append(stringResource(id = R.string.forgetPassword))
            }
            ClickableText(
                text = forgetPass,
                onClick = { viewModel.onEvent(LoginEvent.ForgetPassClicked) },
                style = TextStyle(color = MaterialTheme.colors.error)
            )
        }


    }
}