package by.chekun.repository

import by.chekun.repository.database.AppDatabase
import by.chekun.repository.database.entity.User
import by.chekun.repository.database.entity.advertisement.AddAdvertisementRequest
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import by.chekun.repository.database.entity.user.AccessTokenDTO
import by.chekun.repository.database.entity.user.LoginRequest
import by.chekun.repository.server.ServerCommunicator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import retrofit2.Call


class AppRepository(private val serverCommunicator: ServerCommunicator, private val mainDatabase: AppDatabase) {

    fun getAllAdvertisements(): Single<List<AdvertisementResp>?> {
        return serverCommunicator.getAllAdvertisements()
                .flatMap { list ->
                    //mainDatabase.advertisementsDao().insertList(list.body().adds)
                    Single.just(list.body()?.adds)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCar(id: Long): Single<AdvertisementResp> {
        return serverCommunicator.getCar(id)
//                .map {
//                    //val user = mainDatabase.carDao().getById(id)
//                  //  user
//                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    fun saveCar(add: AddAdvertisementRequest): Call<AdvertisementResp> {
        return serverCommunicator.saveCar(add)
    }

    fun postImage(carId: Long, picture: MultipartBody.Part): Call<AdvertisementResp> {
        return serverCommunicator.postImage(carId, picture)
    }

    fun login(req: LoginRequest): Call<AccessTokenDTO> {
        return serverCommunicator.login(req)
    }


    fun saveUser(user: User) {
        val user = mainDatabase.userDao().saveUser(user = user)

    }

    fun getCurrentUser(): User {
        val user = mainDatabase.userDao().getMe()
        return user
    }
}