package m.derakhshan.todone.feature_authentication.presentation.login.composable

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import m.derakhshan.todone.R
import m.derakhshan.todone.feature_authentication.presentation.login.LoginEvent
import m.derakhshan.todone.feature_authentication.presentation.login.LoginViewModel


@ExperimentalAnimationApi
@Composable
fun LoginForm(show: Boolean) {

    val viewModel: LoginViewModel = viewModel()
    val state = viewModel.state.value

    AnimatedVisibility(
        visible = show,
        enter = slideInVertically(animationSpec = tween(durationMillis = 1000)),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 500),
            targetOffsetY = { -it * 3 / 5 })
    ) {
        Column(Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .weight(0.5f),
                verticalArrangement = Arrangement.Top,
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
                    onClick = { Log.i("Log", "LoginForm: clicked") },
                    style = TextStyle(color = MaterialTheme.colors.error)
                )
            }
        }

    }
}