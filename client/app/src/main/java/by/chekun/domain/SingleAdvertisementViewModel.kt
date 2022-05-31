package by.chekun.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import by.chekun.presentation.widget.SingleLiveEvent
import by.chekun.repository.AppRepository
import by.chekun.repository.database.entity.advertisement.AddAdvertisementRequest
import by.chekun.repository.database.entity.advertisement.AdvertisementRatingRequest
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import by.chekun.repository.database.entity.user.TextResp
import retrofit2.Call


class SingleAdvertisementViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {

    private val liveDataItem = SingleLiveEvent<AdvertisementResp>()

/*    @SuppressLint("CheckResult")
    fun getItem(id: Long) {
        mRepository.getAdvertisement(id).subscribe { list -> liveDataItem.value = list }
    }*/

    fun getItem(advId: Long): Call<AdvertisementResp> {
        return mRepository.getAdv(advId)
    }

    fun addRating(req: AdvertisementRatingRequest): Call<TextResp> {
        return mRepository.addRating(req)
    }

/*    fun getLiveDataItem(): LiveData<AdvertisementResp> {
        return liveDataItem
    }*/
}