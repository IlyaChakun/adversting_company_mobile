package by.chekun.domain

import android.app.Application
import by.chekun.repository.AppRepository
import by.chekun.repository.database.entity.brand.BrandResponse
import by.chekun.repository.database.entity.car.CarRequestDto
import by.chekun.repository.database.entity.car.chassis.ChassisComponent
import by.chekun.repository.database.entity.car.equipment.EquipmentComponent
import by.chekun.repository.database.entity.car.interior.InteriorComponent
import by.chekun.repository.database.entity.car.view.CarDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call

class AddCarViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {

    fun saveCar(car: CarRequestDto): Call<CarDto> {
        return mRepository.saveCar(car)
    }

    fun getBrands(): Call<BrandResponse>? {
        return mRepository.getAllBrands()
    }

    fun getEquipment(): Call<EquipmentComponent>? {
        return mRepository.getEquipment()
    }

    fun getChassis(): Call<ChassisComponent> {
        return mRepository.getChassis()
    }

    fun getInterior(): Call<InteriorComponent> {
        return mRepository.getInterior()
    }

    fun postImage(carId: Long, picture: MultipartBody.Part): Call<CarDto> {
        return mRepository.postImage(carId, picture)
    }


}