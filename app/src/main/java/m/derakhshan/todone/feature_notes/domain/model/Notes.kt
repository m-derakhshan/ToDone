package m.derakhshan.todone.feature_notes.domain.model

import androidx.compose.ui.graphics.Color
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

    val isVisible: Boolean = true
) {
    companion object {
        val noteColors = listOf(
            VeryLightBlue, VeryLightGreen, VeryLightRed,
            VeryLightOrange, VeryLightYellow, VeryLightPurple
        )

        fun getLightColor(color: Color): Color {
            return when (color) {
                VeryLightBlue -> LightBlue
                VeryLightGreen -> LightGreen
                VeryLightRed -> LightRed
                VeryLightOrange -> LightOrange
                VeryLightYellow -> LightYellow
                else -> LightPurple
            }
        }

        fun getOriginalColor(color: Color): Color {
            return when (color) {
                VeryLightBlue -> Blue
                VeryLightGreen -> Green
                VeryLightRed -> Red
                VeryLightOrange -> Orange
                VeryLightYellow -> Yellow
                else -> Purple
            }
        }
    }
}