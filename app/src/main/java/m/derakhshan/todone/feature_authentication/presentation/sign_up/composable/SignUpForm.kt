package m.derakhshan.todone.feature_authentication.presentation.sign_up.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import m.derakhshan.todone.R
import m.derakhshan.todone.feature_authentication.domain.model.ServerResponse
import m.derakhshan.todone.feature_authentication.presentation.login.LoginEvent
import m.derakhshan.todone.feature_authentication.presentation.login.composable.LoadingButton
import m.derakhshan.todone.feature_authentication.presentation.sign_up.SignUpEvent
import m.derakhshan.todone.feature_authentication.presentation.sign_up.SignUpViewModel


@ExperimentalAnimationApi
@Composable
fun SignUpForm(show: Boolean, snackBarMsg: (String, Boolean) -> Unit) {

    val viewModel: SignUpViewModel = hiltViewModel()
    val state = viewModel.state.value

    state.serverResponse?.let { response ->
        if (response is ServerResponse.Success)
            snackBarMsg(response.success, response.code == 200).also {
                viewModel.onEvent(SignUpEvent.DeleteSnackbar)
            }
        else if (response is ServerResponse.Failed)
            snackBarMsg(response.error, response.code == 200).also {
                viewModel.onEvent(SignUpEvent.DeleteSnackbar)
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
            modifier = Modifier
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.joinUs),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                value = state.username,
                onValueChange = { viewModel.onEvent(SignUpEvent.UsernameChanged(it)) },
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.username
                        )
                    )
                },
            )

            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(SignUpEvent.EmailChanged(it)) },
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.email
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )

            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(SignUpEvent.PasswordChanged(it)) },
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
                        viewModel.onEvent(SignUpEvent.TogglePasswordVisibility)
                    }) {
                        Icon(imageVector = image, "show or hide password")
                    }
                }
            )

            //--------------------(sing up button)--------------------//
            LoadingButton(text = R.string.signup, expand = state.isSignUpButtonExpanded) {
                viewModel.onEvent(SignUpEvent.SignUpClicked)
            }
            //--------------------(end of sing up button)--------------------//

        }


    }
}