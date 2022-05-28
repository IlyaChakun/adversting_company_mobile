package by.chekun.repository.server

import by.chekun.repository.database.entity.brand.BrandResponse
import by.chekun.repository.database.entity.car.AddRequestDto
import by.chekun.repository.database.entity.car.AddResponse
import by.chekun.repository.database.entity.car.chassis.ChassisComponent
import by.chekun.repository.database.entity.car.equipment.EquipmentComponent
import by.chekun.repository.database.entity.car.interior.InteriorComponent
import by.chekun.repository.database.entity.car.view.AdvertisementResp
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    //    @GET("/cars")
//    fun getCars(): Single<Response<Array<CarDto>>>
    @GET("/cars")
    fun getCars(): Single<Response<AddResponse>>

    @GET("/cars/{id}")
    fun getCarById(@Path("id") id: Long): Single<AdvertisementResp>


    @POST("/cars")
    fun saveCar(@Body addRequest: AddRequestDto): Call<AdvertisementResp>

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

    @GET("/brands")
    fun getBrands(): Call<BrandResponse>

    @GET("/components/interior")
    fun getInterior(): Call<InteriorComponent>

    @GET("/components/equipment")
    fun getEquipment(): Call<EquipmentComponent>

    @GET("/components/chassis")
    fun getChassis(): Call<ChassisComponent>


}