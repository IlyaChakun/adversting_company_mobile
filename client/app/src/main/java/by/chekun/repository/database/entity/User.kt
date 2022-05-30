package by.chekun.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
        @PrimaryKey
        val id: Long,

        val firstName: String,
        val lastName: String,
        val email: String,

        val accessToken: String? = null,
        val tokenType: String? = null,
        val expiresIn: Long = 0,
        var role: String? = null
)
