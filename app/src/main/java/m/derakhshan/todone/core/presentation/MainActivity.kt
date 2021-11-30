package m.derakhshan.todone.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import dagger.hilt.android.AndroidEntryPoint
import m.derakhshan.todone.core.Setting
import m.derakhshan.todone.ui.theme.ToDoneTheme
import javax.inject.Inject

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var setting: Setting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoneTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainAppUI(setting = setting)
                }
            }
        }

    }
}