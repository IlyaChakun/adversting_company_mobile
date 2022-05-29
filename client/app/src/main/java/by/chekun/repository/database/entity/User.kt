package by.chekun.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.chekun.repository.database.entity.user.AccessTokenDTO

@Entity(tableName = "users")
data class User(
        @PrimaryKey
        val id: Long,

        val firstName: String,
        val lastName: String,
        val email: String,

        val accessTokenDTO: AccessTokenDTO

        //roles
)
