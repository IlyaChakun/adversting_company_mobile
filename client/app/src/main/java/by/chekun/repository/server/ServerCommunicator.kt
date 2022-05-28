package by.chekun.repository.server

import android.util.Log
import by.chekun.repository.database.entity.brand.BrandResponse
import by.chekun.repository.database.entity.car.AddRequestDto
import by.chekun.repository.database.entity.car.AddResponse
import by.chekun.repository.database.entity.car.chassis.ChassisComponent
import by.chekun.repository.database.entity.car.equipment.EquipmentComponent
import by.chekun.repository.database.entity.car.interior.InteriorComponent
import by.chekun.repository.database.entity.car.view.AdvertisementResp
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.TimeUnit


class ServerCommunicator(private val mService: ApiService) {

    companion object {
        private const val DEFAULT_TIMEOUT = 10
        private const val DEFAULT_RETRY_ATTEMPTS = 4L
    }

    fun getAllCars(): Single<Response<AddResponse>> {
        return mService.getCars()
                .compose(singleTransformer())
                .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message.toString()) }
    }

    fun getCar(id: Long): Single<AdvertisementResp> {
        return mService.getCarById(id).compose(singleTransformer())
    }

    fun saveCar(add: AddRequestDto): Call<AdvertisementResp> {
        return mService.saveCar(add)
    }


    fun getBrands(): Call<BrandResponse>? {
        return mService.getBrands()
    }

    fun getEquipment(): Call<EquipmentComponent>? {
        return mService.getEquipment()
    }

    fun getChassis(): Call<ChassisComponent> {
        return mService.getChassis()
    }

    fun getInterior(): Call<InteriorComponent> {
        return mService.getInterior()
    }

    fun createPartFromString(value: String): RequestBody {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, value)

    }

    fun postImage(carId: Long, picture: MultipartBody.Part): Call<AdvertisementResp> {
        return mService.postImage(carId, picture)
    }

    private fun <T> singleTransformer(): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY_ATTEMPTS)
    }

    private fun <T> observableTransformer(): ObservableTransformer<T, T> = ObservableTransformer {
        it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY_ATTEMPTS)
    }
}


