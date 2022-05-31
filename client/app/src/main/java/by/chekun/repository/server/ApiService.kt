package by.chekun.repository.server

import by.chekun.repository.database.entity.advertisement.AddAdvertisementRequest
import by.chekun.repository.database.entity.advertisement.AddResponse
import by.chekun.repository.database.entity.advertisement.RespChangeStatus
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import by.chekun.repository.database.entity.user.*
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

    @GET("/api/v1/advertisements/admin")
    fun getAdminAdvertisements(): Single<Response<AddResponse>>

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

    @POST("/api/v1/auth")
    fun login(@Body loginRequest: LoginRequest): Call<AccessTokenDTO>

    @POST("/api/v1/auth/registration")
    fun register(@Body registerRequest: RegisterRequest): Call<TextResp>

    @GET("/api/v1/users")
    fun getMe(@HeaderMap headers: Map<String, String>): Call<UserResp>

    @POST("/api/v1/advertisements/update-status")
    fun setPublishStatus(@Body respChangeStatus: RespChangeStatus): Call<TextResp>


//    @Multipart
//    @POST("/cars")
//    fun saveCar(@Part carRequest: CarRequestDto): Call<CarDto>

//    @GET("/brands")
//    fun getBrands(): Single<Response<Array<BrandDto>>>


//    @GET("/brands")
//    fun getBrands(): Single<BrandResponse>

}