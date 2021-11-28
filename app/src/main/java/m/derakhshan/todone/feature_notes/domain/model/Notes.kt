package m.derakhshan.todone.feature_notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import m.derakhshan.todone.ui.theme.*


@Entity
data class Notes(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String,
    val color: Int,
    val timestamp: Long,
) {
    companion object {
        val noteColors = listOf(
            LightBlue, LightGreen, LightRed,
            LightOrange, LightYellow, LightPurple
        )
    }
}