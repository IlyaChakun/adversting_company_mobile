package by.chekun.repository

import by.chekun.repository.database.AppDatabase
import by.chekun.repository.database.entity.brand.BrandResponse
import by.chekun.repository.database.entity.car.CarRequestDto
import by.chekun.repository.database.entity.car.chassis.ChassisComponent
import by.chekun.repository.database.entity.car.equipment.EquipmentComponent
import by.chekun.repository.database.entity.car.interior.InteriorComponent
import by.chekun.repository.database.entity.car.view.CarDto
import by.chekun.repository.server.ServerCommunicator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call


class AppRepository(private val serverCommunicator: ServerCommunicator, private val mainDatabase: AppDatabase) {

    fun getAllCars(): Single<List<CarDto>?> {
        return serverCommunicator.getAllCars()
                .flatMap { list ->
                    //mainDatabase.carDao().insertList(list.body().cars)
                    Single.just(list.body()?.cars)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCar(id: Long): Single<CarDto> {
        return serverCommunicator.getCar(id)
//                .map {
//                    //val user = mainDatabase.carDao().getById(id)
//                  //  user
//                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    fun saveCar(car: CarRequestDto): Call<CarDto> {
        return serverCommunicator.saveCar(car)
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



    fun postImage(carId: Long, picture: MultipartBody.Part): Call<CarDto> {
        return serverCommunicator.postImage(carId, picture)
    }

}