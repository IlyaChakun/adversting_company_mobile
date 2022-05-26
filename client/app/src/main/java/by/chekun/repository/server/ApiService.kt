package by.chekun.repository.server

import by.chekun.repository.database.entity.brand.BrandResponse
import by.chekun.repository.database.entity.car.CarRequestDto
import by.chekun.repository.database.entity.car.CarResponse
import by.chekun.repository.database.entity.car.chassis.ChassisComponent
import by.chekun.repository.database.entity.car.equipment.EquipmentComponent
import by.chekun.repository.database.entity.car.interior.InteriorComponent
import by.chekun.repository.database.entity.car.view.CarDto
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    //    @GET("/cars")
//    fun getCars(): Single<Response<Array<CarDto>>>
    @GET("/cars")
    fun getCars(): Single<Response<CarResponse>>

    @GET("/cars/{id}")
    fun getCarById(@Path("id") id: Long): Single<CarDto>


    @POST("/cars")
    fun saveCar(@Body carRequest: CarRequestDto): Call<CarDto>

//    @Multipart
//    @POST("/cars/picture")
//    fun postImage(@Part picture: MultipartBody.Part?, @Part car: CarRequestDto ): Call<ResponseBody?>?

    @Multipart
    @PATCH("/cars/{id}")
    fun postImage(@Path("id") id: Long ,
                  @Part picture: MultipartBody.Part
    )
            : Call<CarDto>

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