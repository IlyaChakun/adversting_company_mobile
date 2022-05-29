package by.chekun.repository.database.entity.user

class UserResp {
    var id: Long? = null
    var firstName: String? = null
    var lastName: String? = null
    var email: String? = null
    var roles: List<UserRoleDTO>? = null
    var imageUrl: String? = null
    var password: String? = null
}