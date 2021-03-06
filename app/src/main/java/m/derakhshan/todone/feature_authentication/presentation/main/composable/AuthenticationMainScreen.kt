package m.derakhshan.todone.feature_authentication.presentation.main.composable


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import m.derakhshan.todone.R
import m.derakhshan.todone.core.presentation.BackSwipeGesture
import m.derakhshan.todone.core.presentation.NavGraph
import m.derakhshan.todone.feature_authentication.presentation.login.composable.LoginForm
import m.derakhshan.todone.feature_authentication.presentation.main.MainEvent
import m.derakhshan.todone.feature_authentication.presentation.main.MainViewModel
import m.derakhshan.todone.feature_authentication.presentation.sign_up.composable.SignUpForm

@ExperimentalAnimationApi
@Composable
fun AuthenticationMainScreen(
    navController: NavController
) {

    val viewModel: MainViewModel = hiltViewModel()
    val state = viewModel.state.value

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()


    var offset by remember { mutableStateOf(0f) }


    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.draggable(
            orientation = Orientation.Horizontal,
            state = rememberDraggableState { delta ->
                if (state.signUpFormIsVisible || state.loginFormIsVisible)
                    offset += (delta * 0.2f)
            }, onDragStopped = {
                if (offset > 90)
                    viewModel.onEvent(MainEvent.BackButtonClick)
                offset = 0f
            }
        )
    )
    {


        //--------------------(lottie animation)--------------------//
        RoundedTriangle(modifier = Modifier.fillMaxSize())
        LottieWelcome()

        //--------------------(login and sign up button)--------------------//
        Row(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (!state.signUpFormIsVisible && !state.loginFormIsVisible) {
                Button(
                    onClick = {
                        viewModel.onEvent(event = MainEvent.LoginButtonClick)
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(0.5f)
                ) {
                    Text(text = stringResource(id = R.string.login))
                }

                Button(
                    onClick = { viewModel.onEvent(event = MainEvent.SignUpButtonClick) },
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(0.5f)
                ) {
                    Text(text = stringResource(id = R.string.signup))
                }
            }

        }

        //--------------------(version)--------------------//
        Version(modifier = Modifier.fillMaxSize())

        //--------------------(change background via circular effect)--------------------//
        ChangeBackground(show = state.loginFormIsVisible || state.signUpFormIsVisible) {
            viewModel.onEvent(MainEvent.BackButtonClick)
        }


        LoginForm(show = state.loginFormIsVisible) { msg, userLoggedIn ->
            if (userLoggedIn)
                navController.navigate(NavGraph.MainScreen.route) {
                    popUpTo(NavGraph.AuthenticationScreen.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            else
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = msg
                    )
                }
        }

        SignUpForm(show = state.signUpFormIsVisible) { msg, userSignedUp ->
            if (userSignedUp)
                navController.navigate(NavGraph.MainScreen.route) {
                    popUpTo(NavGraph.AuthenticationScreen.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            else
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = msg
                    )
                }
        }

        BackSwipeGesture(offset = offset)
    }
}


