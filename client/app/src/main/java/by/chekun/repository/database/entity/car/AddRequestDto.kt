package by.chekun.repository.database.entity.car

import by.chekun.repository.database.entity.car.view.AdvertisementStatus
import by.chekun.repository.database.entity.car.view.AdvertisementType
import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody


class AddRequestDto {

    private val title: String? = null

    private val body: String? = null
    
    private val type: AdvertisementType? = null

    private val status: AdvertisementStatus? = null

    private val userId: Long? = null

    @SerializedName("mileage")
    lateinit var mileage: MileageDto

    var interiorColorId: Long = 0

    var interiorMaterialId: Long = 0

    var safetyIds: List<Long> = ArrayList()

    var interiorIds: List<Long> = ArrayList()

    var engineTypeId: Long = 0

    var engineCapacity: Double = 0.0

    var transmissionTypeId: Long = 0

    var wheelDriveTypeId: Long = 0

    var price: Double = 0.0

    var description: String = ""


    var picture: RequestBody? = null
   //var picture: MultipartBody.Part? = null


}