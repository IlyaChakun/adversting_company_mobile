package by.chekun.repository.server

import by.chekun.repository.database.entity.advertisement.AddAdvertisementRequest
import by.chekun.repository.database.entity.advertisement.AddResponse
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    //    @GET("/cars")
//    fun getCars(): Single<Response<Array<CarDto>>>
    @GET("/api/v1/advertisements")
    fun getAdvertisements(): Single<Response<AddResponse>>

    @GET("/cars/{id}")
    fun getCarById(@Path("id") id: Long): Single<AdvertisementResp>


    @POST("/api/v1/advertisements")
    fun saveCar(@Body addRequest: AddAdvertisementRequest): Call<AdvertisementResp>

//    @Multipart
//    @POST("/cars/picture")
//    fun postImage(@Part picture: MultipartBody.Part?, @Part car: CarRequestDto ): Call<ResponseBody?>?

    @Multipart
    @PATCH("/cars/{id}")
    fun postImage(@Path("id") id: Long ,
                  @Part picture: MultipartBody.Part
    )
            : Call<AdvertisementResp>

//    @Multipart
//    @POST("/cars")
//    fun saveCar(@Part carRequest: CarRequestDto): Call<CarDto>

//    @GET("/brands")
//    fun getBrands(): Single<Response<Array<BrandDto>>>


//    @GET("/brands")
//    fun getBrands(): Single<BrandResponse>

}