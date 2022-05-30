package by.chekun.repository.database.entity.user

import java.time.LocalDateTime

class UserResp {

    val version: Int? = null
    val dateOfCreation: LocalDateTime? = null
    val dateOfLastUpdate: LocalDateTime? = null
    val id: Long? = null
    val firstName: String? = null
    val lastName: String? = null
    val email: String? = null
    val roles: List<UserRoleDTO>? = null
    val imageUrl: String? = null
    val password: String? = null
}