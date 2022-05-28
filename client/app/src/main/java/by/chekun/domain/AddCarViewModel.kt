package by.chekun.domain

import android.app.Application
import by.chekun.repository.AppRepository
import by.chekun.repository.database.entity.brand.BrandResponse
import by.chekun.repository.database.entity.car.AddRequestDto
import by.chekun.repository.database.entity.car.chassis.ChassisComponent
import by.chekun.repository.database.entity.car.equipment.EquipmentComponent
import by.chekun.repository.database.entity.car.interior.InteriorComponent
import by.chekun.repository.database.entity.car.view.AdvertisementResp
import okhttp3.MultipartBody
import retrofit2.Call

class AddCarViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {

    fun saveCar(add: AddRequestDto): Call<AdvertisementResp> {
        return mRepository.saveCar(add)
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

    fun postImage(carId: Long, picture: MultipartBody.Part): Call<AdvertisementResp> {
        return mRepository.postImage(carId, picture)
    }


}