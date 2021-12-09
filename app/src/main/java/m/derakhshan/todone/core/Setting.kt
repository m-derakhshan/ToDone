package m.derakhshan.todone.core

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class Setting @Inject constructor(
    @ApplicationContext context: Context
) {

    private val sharedPreferences = context.getSharedPreferences("share", Context.MODE_PRIVATE)
    private val edit = sharedPreferences.edit()

    val lastNoteAddedId: Int
        get() {
            val id = sharedPreferences.getInt("lastNoteAddedId", 0)
            edit.putInt("lastNoteAddedId", id+1)
            edit.apply()
            return id
        }


    var isUserLoggedIn: Boolean
        set(value) {
            edit.putBoolean("isUserLoggedIn", value)
            edit.apply()
        }
        get() {
            return sharedPreferences.getBoolean("isUserLoggedIn", false)
        }

}