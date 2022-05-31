package by.chekun.repository.database.entity.advertisement

import by.chekun.repository.database.entity.advertisement.view.AdvertisementStatus
import by.chekun.repository.database.entity.advertisement.view.AdvertisementType
import okhttp3.RequestBody


class AddAdvertisementRequest {

    var title: String? = null

    var body: String? = null

    var type: AdvertisementType? = null

    var status: AdvertisementStatus? = null

    var userId: Long? =null

    var picture: String? = null
   //var picture: MultipartBody.Part? = null


}