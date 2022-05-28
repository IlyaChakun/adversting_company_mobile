package by.chekun.repository.database.entity.advertisement

import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import com.google.gson.annotations.SerializedName

data class AddResponse(
        @SerializedName("objects")
        val adds: List<AdvertisementResp>
)