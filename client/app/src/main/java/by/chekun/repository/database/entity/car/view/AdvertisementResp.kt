package by.chekun.repository.database.entity.car.view

import by.chekun.repository.database.entity.AbstractDto
import by.chekun.repository.database.entity.car.chassis.WheelDriveTypeDto
import java.util.*


class AdvertisementResp() : AbstractDto() {

    private val title: String? = null

    private val body: String? = null

    private val picture: String? = null

    private val type: AdvertisementType? = null

    private val status: AdvertisementStatus? = null

    fun getTitle(): String {
        return title.toString()
    }

    fun getBody(): String {
        return body.toString()
    }

    fun getType(): String {
        return type.toString()
    }

    fun getStatus(): String {
        return status.toString()
    }


//    fun getBrandTitleAndModelAndDescription(): String {
//        return brand.title + " " + model.name + " " + generation.generation
//    }
//
//    fun getReleaseYearAndDimensionAndEngineCapacityAndEngineTypeAnd(): String {
//        return releaseYear.releaseYear.toString() + "г, " + mileage.mileage + " " + mileage.measurement + ", " + engineCapacity + "л, " + engineType.engineType + ", " + transmissionType.transmissionType
//    }
//
//    fun getIdString(): String {
//        return "№ $id"
//    }
//
//    fun getUpdatedDate(): String {
//        return "Обновлено $dateOfModification"
//    }
//
//    fun getEngineCapacityString(): String {
//        return "$engineCapacity см3"
//    }

}
