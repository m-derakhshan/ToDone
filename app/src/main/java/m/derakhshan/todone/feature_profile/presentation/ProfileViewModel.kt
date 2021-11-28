package m.derakhshan.todone.feature_profile.presentation

import android.app.Activity
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import m.derakhshan.todone.core.Setting
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val setting: Setting
) : ViewModel() {
    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    fun onEvent(event: ProfileEvent, activity: Activity? = null) {
        when (event) {
            is ProfileEvent.ExitClicked -> {
                activity?.let {
                    setting.isUserLoggedIn = false
                    it.finish()
                }
            }
        }

    }

}