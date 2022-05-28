package by.chekun.repository

import by.chekun.repository.database.AppDatabase
import by.chekun.repository.database.entity.brand.BrandResponse
import by.chekun.repository.database.entity.car.AddRequestDto
import by.chekun.repository.database.entity.car.chassis.ChassisComponent
import by.chekun.repository.database.entity.car.equipment.EquipmentComponent
import by.chekun.repository.database.entity.car.interior.InteriorComponent
import by.chekun.repository.database.entity.car.view.AdvertisementResp
import by.chekun.repository.server.ServerCommunicator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import retrofit2.Call


class AppRepository(private val serverCommunicator: ServerCommunicator, private val mainDatabase: AppDatabase) {

    fun getAllCars(): Single<List<AdvertisementResp>?> {
        return serverCommunicator.getAllCars()
                .flatMap { list ->
                    //mainDatabase.carDao().insertList(list.body().cars)
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

    fun saveCar(add: AddRequestDto): Call<AdvertisementResp> {
        return serverCommunicator.saveCar(add)
    }

    fun getAllBrands(): Call<BrandResponse>? {
        return serverCommunicator.getBrands()
    }

    fun getEquipment(): Call<EquipmentComponent>? {
        return serverCommunicator.getEquipment()
    }

    fun getChassis(): Call<ChassisComponent> {
        return serverCommunicator.getChassis()
    }

    fun getInterior(): Call<InteriorComponent> {
        return serverCommunicator.getInterior()
    }



    fun postImage(carId: Long, picture: MultipartBody.Part): Call<AdvertisementResp> {
        return serverCommunicator.postImage(carId, picture)
    }

}