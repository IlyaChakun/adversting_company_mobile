package by.chekun.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import by.chekun.R
import by.chekun.presentation.widget.SingleLiveEvent
import by.chekun.repository.AppRepository
import by.chekun.repository.database.entity.advertisement.RespChangeStatus
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import by.chekun.repository.database.entity.user.RegisterRequest
import by.chekun.repository.database.entity.user.TextResp
import retrofit2.Call

class PendingAdvertisementsViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {
    private val liveDataItems = SingleLiveEvent<List<AdvertisementResp>>()

    @SuppressLint("CheckResult")
    fun getAllItems() {
        mRepository.getPendingAdvertisements().subscribe { list -> liveDataItems.value = list }
    }

    fun getLiveDataItems(): LiveData<List<AdvertisementResp>> {
        return liveDataItems
    }

    fun setPublishStatus(respChangeStatus: RespChangeStatus): Call<TextResp> {
        return mRepository.setPublishStatus(respChangeStatus)
    }

}