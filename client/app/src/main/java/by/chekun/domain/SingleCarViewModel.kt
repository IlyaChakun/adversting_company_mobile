package by.chekun.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import by.chekun.presentation.widget.SingleLiveEvent
import by.chekun.repository.AppRepository
import by.chekun.repository.database.entity.car.view.CarDto


class SingleCarViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {

    private val liveDataItem = SingleLiveEvent<CarDto>()

    @SuppressLint("CheckResult")
    fun getItem(id: Long) {
        mRepository.getCar(id).subscribe { list -> liveDataItem.value = list }
    }

    fun getLiveDataItem(): LiveData<CarDto> {
        return liveDataItem
    }
}