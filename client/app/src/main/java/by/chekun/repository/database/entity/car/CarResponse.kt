package by.chekun.repository.database.entity.car

import by.chekun.repository.database.entity.car.view.CarDto
import com.google.gson.annotations.SerializedName

data class CarResponse(
        @SerializedName("objects")
        val cars: List<CarDto>
)