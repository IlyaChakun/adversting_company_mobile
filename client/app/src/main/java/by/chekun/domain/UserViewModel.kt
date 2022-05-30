package by.chekun.domain

import android.app.Application
import by.chekun.repository.AppRepository
import by.chekun.repository.database.entity.User
import by.chekun.repository.database.entity.user.*
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.HeaderMap

class UserViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {


    fun login(req: LoginRequest): Call<AccessTokenDTO> {
        return mRepository.login(req)
    }

    fun getCurrentUser(): User {
        return mRepository.getCurrentUser()
    }

    fun saveUser(user: User) {
        mRepository.saveUser(user)
    }

    fun register(req: RegisterRequest): Call<TextResp> {
        return mRepository.register(req)
    }

    fun getMe(headers: Map<String, String>): Call<UserResp> {
        return mRepository.getMe(headers)
    }

    fun deleteAll() {
        mRepository.deleteAll()
    }

}