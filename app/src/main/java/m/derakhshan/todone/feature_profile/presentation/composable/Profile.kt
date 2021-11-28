package m.derakhshan.todone.feature_profile.presentation.composable

import android.app.Activity
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import m.derakhshan.todone.R
import m.derakhshan.todone.feature_profile.presentation.ProfileEvent
import m.derakhshan.todone.feature_profile.presentation.ProfileViewModel


@Composable
fun Profile(modifier: Modifier = Modifier) {

    val viewModel: ProfileViewModel = hiltViewModel()
    val activity = (LocalContext.current as? Activity)

    Scaffold(modifier = modifier) {

        Button(onClick = { viewModel.onEvent(ProfileEvent.ExitClicked, activity = activity) }) {
            Text(text = stringResource(id = R.string.exit))
        }

    }
}