package by.chekun.repository.database.entity.car

import com.google.gson.annotations.SerializedName


class MileageDto {

    @SerializedName("mileage")
    var mileage: Int = 0

    @SerializedName("measurement")
    var measurement: String = "km"//измерение


    fun getMileageString(): String {
        return "$mileage $measurement"
    }
}

