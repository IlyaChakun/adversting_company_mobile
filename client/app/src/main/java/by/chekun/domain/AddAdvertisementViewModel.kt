package by.chekun.domain

import android.app.Application
import by.chekun.repository.AppRepository
import by.chekun.repository.database.entity.advertisement.AddAdvertisementRequest
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import okhttp3.MultipartBody
import retrofit2.Call

class AddAdvertisementViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {

    fun saveAdvertisement(add: AddAdvertisementRequest): Call<AdvertisementResp> {
        return mRepository.saveCar(add)
    }

    fun postImage(carId: Long, picture: MultipartBody.Part): Call<AdvertisementResp> {
        return mRepository.postImage(carId, picture)
    }


}