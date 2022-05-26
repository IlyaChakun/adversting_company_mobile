package by.chekun.repository.database.entity.car.view

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import by.chekun.repository.database.entity.AbstractDto
import by.chekun.repository.database.entity.brand.BodyTypeDto
import by.chekun.repository.database.entity.car.MileageDto
import by.chekun.repository.database.entity.car.chassis.EngineTypeDto
import by.chekun.repository.database.entity.car.chassis.TransmissionTypeDto
import by.chekun.repository.database.entity.car.chassis.WheelDriveTypeDto
import by.chekun.repository.database.entity.car.equipment.ColorDto
import by.chekun.repository.database.entity.car.equipment.ConditionDto
import by.chekun.repository.database.entity.car.equipment.SafetyDto
import by.chekun.repository.database.entity.car.interior.InteriorColorDto
import by.chekun.repository.database.entity.car.interior.InteriorDto
import by.chekun.repository.database.entity.car.interior.InteriorMaterialDto
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.collections.HashSet


class CarDto() : AbstractDto() {

    lateinit var brand: CarBrandDto

    lateinit var model: CarModelDto

    lateinit var releaseYear: CarReleaseYearDto

    lateinit var generation: CarGenerationDto

    lateinit var bodyType: BodyTypeDto

    lateinit var color: ColorDto

    lateinit var condition: ConditionDto

    lateinit var mileage: MileageDto

    lateinit var interiorColor: InteriorColorDto

    lateinit var interiorMaterial: InteriorMaterialDto

    var safeties: Set<SafetyDto> = HashSet()

    var interior: Set<InteriorDto> = HashSet()

    lateinit var engineType: EngineTypeDto

    var engineCapacity: Double = 0.0

    lateinit var transmissionType: TransmissionTypeDto

    lateinit var wheelDriveType: WheelDriveTypeDto

    var price: Double = 0.0

    lateinit var description: String

    var picture: String? = null


    var dateOfCreation: String? = null

    var dateOfModification: String? = null


    fun getPriceString(): String {
        return "$price руб."
    }


    fun getBrandTitleAndModelAndDescription(): String {
        return brand.title + " " + model.name + " " + generation.generation
    }

    fun getReleaseYearAndDimensionAndEngineCapacityAndEngineTypeAnd(): String {
        return releaseYear.releaseYear.toString() + "г, " + mileage.mileage + " " + mileage.measurement + ", " + engineCapacity + "л, " + engineType.engineType + ", " + transmissionType.transmissionType
    }

    fun getIdString(): String {
        return "№ $id"
    }

    fun getUpdatedDate(): String {
        return "Обновлено $dateOfModification"
    }

    fun getEngineCapacityString(): String {
        return "$engineCapacity см3"
    }

}
