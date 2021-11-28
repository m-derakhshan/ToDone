package m.derakhshan.todone.feature_home.presentation.composable

import m.derakhshan.todone.R

data class HomeState(
    val clock: HomeClock = HomeClock(),
    val greetings: Int = R.string.nothing
)


data class HomeClock(
    val second: Int = 0,
    val minute: Int = 0,
    val hour: Int = 0
)