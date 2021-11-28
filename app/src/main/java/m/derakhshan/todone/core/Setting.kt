package m.derakhshan.todone.core

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class Setting @Inject constructor(
    @ApplicationContext context: Context
) {

    private val sharedPreferences = context.getSharedPreferences("share", Context.MODE_PRIVATE)
    private val edit = sharedPreferences.edit()


    var isUserLoggedIn: Boolean
        set(value) {
            edit.putBoolean("isUserLoggedIn", value)
            edit.apply()
        }
        get() {
            return sharedPreferences.getBoolean("isUserLoggedIn", false)
        }

}