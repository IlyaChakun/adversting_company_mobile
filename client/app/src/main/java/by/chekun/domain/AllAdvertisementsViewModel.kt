package by.chekun.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import by.chekun.presentation.widget.SingleLiveEvent
import by.chekun.repository.AppRepository

class AllAdvertisementsViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {
    private val liveDataItems = SingleLiveEvent<List<AdvertisementResp>>()

    @SuppressLint("CheckResult")
    fun getAllItems() {
        mRepository.getAllAdvertisements().subscribe { list -> liveDataItems.value = list }
    }

    @SuppressLint("CheckResult")
    fun getPublishItems() {
        mRepository.getPublishAdvertisements().subscribe { list -> liveDataItems.value = list }
    }

    fun getLiveDataItems(): LiveData<List<AdvertisementResp>> {
        return liveDataItems
    }
}