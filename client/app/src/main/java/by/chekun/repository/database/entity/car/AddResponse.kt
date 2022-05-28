package by.chekun.repository.database.entity.car

import by.chekun.repository.database.entity.car.view.AdvertisementResp
import com.google.gson.annotations.SerializedName

data class AddResponse(
        @SerializedName("objects")
        val adds: List<AdvertisementResp>
)