package m.derakhshan.todone.feature_authentication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import m.derakhshan.todone.feature_authentication.presentation.main.composable.MainAuthentication
import m.derakhshan.todone.ui.theme.ToDoneTheme

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

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
}