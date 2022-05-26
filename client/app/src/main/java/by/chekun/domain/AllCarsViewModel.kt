package by.chekun.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import by.chekun.repository.database.entity.car.view.CarDto
import by.chekun.presentation.widget.SingleLiveEvent
import by.chekun.repository.AppRepository

class AllCarsViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {
    private val liveDataItems = SingleLiveEvent<List<CarDto>>()

    @SuppressLint("CheckResult")
    fun getAllItems() {
        mRepository.getAllCars().subscribe { list -> liveDataItems.value = list }

//        val model: String = ""
//        val generation: String = ""
//        val mileage: Double = 5.0
//        val bodyType: String = "body"
//        val transmissionType: String = ""
//        val fuelType: String = ""
//        val wheelDriveType: String = ""
//        val engineCapacity: Double = 0.0
//        val releaseYear: Int = 1555
//        val price: Double = 15.0
//        val description: String = ""
//
//        val car = CarDto(1, model, generation, mileage, bodyType, transmissionType, fuelType, wheelDriveType, engineCapacity, releaseYear, price, description, Brand(""))
//
//        liveDataItems.value = listOf(car)
    }

    fun getLiveDataItems(): LiveData<List<CarDto>> {
        return liveDataItems
    }
}