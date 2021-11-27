package m.derakhshan.todone.feature_authentication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    @PrimaryKey
    val token: String = "",

    val name: String = "",

    val username: String = "",

    val email: String = "",

    val phone: String = ""
)
