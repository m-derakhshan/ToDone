package m.derakhshan.todone.core

import android.content.Context
import javax.inject.Inject


class Setting @Inject constructor(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("share", Context.MODE_PRIVATE)
    private val edit = sharedPreferences.edit()


    var isUserLoggedIn: Boolean
        set(value) {
            edit.putBoolean("isUserLoggedIn", value)
        }
        get() {
            return sharedPreferences.getBoolean("isUserLoggedIn", false)
        }

}