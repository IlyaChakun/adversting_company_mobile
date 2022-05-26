package by.chekun.repository.database.pojo

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class CarRequest(

        @SerializedName("model")
        val model: String,

        @SerializedName("generation")
        val generation: String,

        @SerializedName("mileage")
        val mileage: Double,

        @SerializedName("bodyType")
        val bodyType: String,

        @SerializedName("transmissionType")
        val transmissionType: String,

        @SerializedName("fuelType")
        val fuelType: String,

        @SerializedName("wheelDriveType")
        val wheelDriveType: String,

        @SerializedName("engineCapacity")
        val engineCapacity: Double,

        @SerializedName("releaseYear")
        val releaseYear: Int,

        @SerializedName("price")
        val price: Double,

        @SerializedName("description")
        val description: String,

        @SerializedName("brandId")
        val brandId: Long
)