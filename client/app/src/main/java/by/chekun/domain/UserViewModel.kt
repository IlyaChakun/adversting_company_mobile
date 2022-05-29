package by.chekun.domain

import android.app.Application
import by.chekun.repository.AppRepository
import by.chekun.repository.database.entity.User
import by.chekun.repository.database.entity.user.AccessTokenDTO
import by.chekun.repository.database.entity.user.LoginRequest
import retrofit2.Call

class UserViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {


    fun login(req: LoginRequest): Call<AccessTokenDTO> {
        return mRepository.login(req)
    }

    fun getMe(): User {
        return mRepository.getCurrentUser()
    }

    fun saveUser(user: User) {
        mRepository.saveUser(user)
    }

}