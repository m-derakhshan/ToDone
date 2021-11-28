package m.derakhshan.todone.feature_home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import m.derakhshan.todone.R
import m.derakhshan.todone.feature_home.presentation.composable.HomeClock
import m.derakhshan.todone.feature_home.presentation.composable.HomeEvent
import m.derakhshan.todone.feature_home.presentation.composable.HomeState
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        startClock()
        calculateGreetings()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
        }
    }

    private fun calculateGreetings() {
        val time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        _state.value = _state.value.copy(
            greetings = when {
                time == 0 -> R.string.good_mid_night
                time < 3 -> R.string.good_night
                time < 12 -> R.string.good_morning
                time < 17 -> R.string.good_after_noon
                time < 20 -> R.string.good_evening
                else -> R.string.good_night
            }
        )
    }

    private fun startClock() {
        viewModelScope.launch {
            var time = Calendar.getInstance()
            while (true) {
                _state.value = _state.value.copy(
                    clock = HomeClock(
                        second = time.get(Calendar.SECOND),
                        minute = time.get(Calendar.MINUTE),
                        hour = time.get(Calendar.HOUR_OF_DAY)
                    )
                )
                delay(1000)
                time = Calendar.getInstance()
            }
        }
    }
}