package m.derakhshan.todone.feature_authentication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import dagger.hilt.android.AndroidEntryPoint
import m.derakhshan.todone.feature_authentication.presentation.main.MainEvent
import m.derakhshan.todone.feature_authentication.presentation.main.MainViewModel
import m.derakhshan.todone.feature_authentication.presentation.main.composable.MainAuthentication
import m.derakhshan.todone.ui.theme.ToDoneTheme

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoneTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainAuthentication()
                }
            }
        }

    }

    override fun onBackPressed() {
        if (mainViewModel.state.value.loginFormIsVisible ||
            mainViewModel.state.value.signUpFormIsVisible
        )
            mainViewModel.onEvent(MainEvent.BackButtonClick)
        else
            super.onBackPressed()
    }
}