package by.chekun.repository.server

import android.util.Log
import by.chekun.repository.database.entity.advertisement.AddAdvertisementRequest
import by.chekun.repository.database.entity.advertisement.AddResponse
import by.chekun.repository.database.entity.advertisement.AdvertisementRatingRequest
import by.chekun.repository.database.entity.advertisement.RespChangeStatus
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import by.chekun.repository.database.entity.user.*
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

    fun getAllAdvertisements(): Single<Response<AddResponse>> {

        return mService.getAdvertisements()
                .compose(singleTransformer())
                .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message.toString()) }
    }

    fun getPublishAdvertisements(): Single<Response<AddResponse>> {

        return mService.getPublishAdvertisements()
                .compose(singleTransformer())
                .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message.toString()) }
    }

    fun getPendingAdvertisements(): Single<Response<AddResponse>> {

        return mService.getAdminAdvertisements()
                .compose(singleTransformer())
                .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message.toString()) }
    }

    fun getAdvertisement(id: Long): Call<AdvertisementResp> {
        return mService.getAdvertisementById(id)
    }

    fun addAdvertisementRating(req: AdvertisementRatingRequest): Call<TextResp> {
        return mService.addAdvertisementRating(req)
    }

    fun saveCar(add: AddAdvertisementRequest): Call<AdvertisementResp> {
        return mService.saveCar(add)
    }

    fun createPartFromString(value: String): RequestBody {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, value)

    }

    fun postImage(carId: Long, picture: MultipartBody.Part): Call<AdvertisementResp> {
        return mService.postImage(carId, picture)
    }

    fun login(req: LoginRequest): Call<AccessTokenDTO> {
        return mService.login(req)
    }

    fun register(req: RegisterRequest): Call<TextResp> {
        return mService.register(req)
    }

    fun getMe(header: Map<String, String>): Call<UserResp> {
        return mService.getMe(header)
    }

    fun setPublishStatus(respChangeStatus: RespChangeStatus): Call<TextResp> {
        return mService.setPublishStatus(respChangeStatus)
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


