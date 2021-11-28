package m.derakhshan.todone.feature_authentication.presentation.main.composable


import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import m.derakhshan.todone.R
import m.derakhshan.todone.core.presentation.NavGraph
import m.derakhshan.todone.feature_authentication.presentation.login.composable.LoginForm
import m.derakhshan.todone.feature_authentication.presentation.main.MainEvent
import m.derakhshan.todone.feature_authentication.presentation.main.MainViewModel
import m.derakhshan.todone.feature_authentication.presentation.sign_up.composable.SignUpForm
import m.derakhshan.todone.ui.theme.*

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
                if (offset > 100)
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


        Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
            val path = Path().apply {
                moveTo(0.0f, 0.0f)
                lineTo(offset, size.height / 2f)
                lineTo(0f, size.height)
            }
            drawIntoCanvas { canvas ->
                canvas.drawOutline(
                    outline = Outline.Generic(path = path),
                    paint = Paint().apply {
                        color = LightBlue
                        pathEffect = PathEffect.cornerPathEffect(size.height / 2)
                    }
                )
            }
        })

        //--------------------(arrow back for swipe gesture)--------------------//
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = "Back",
                tint = White,
                modifier = Modifier
                    .offset(x = (-50 + offset*0.3 ).dp, 0.dp)
                    .width(40.dp)
            )
        }


    }
}

