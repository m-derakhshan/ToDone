package m.derakhshan.todone.feature_home.presentation.composable

import android.app.Activity
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import m.derakhshan.todone.core.presentation.BackSwipeGesture
import m.derakhshan.todone.feature_home.presentation.HomeViewModel

@Composable
fun Home() {

    val viewModel: HomeViewModel = hiltViewModel()
    val state: HomeState = viewModel.state.value

    Scaffold { padding ->

        //--------------------(clock and greetings)--------------------//
        Column(modifier = Modifier.padding(padding)) {
            Text(
                text = stringResource(id = state.greetings),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 5.dp, start = 10.dp, bottom = 5.dp)
            )
            Clock(
                clock = state.clock, modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }
    }
}