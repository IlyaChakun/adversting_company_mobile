package by.chekun.repository.database.entity.advertisement.view

import by.chekun.repository.database.entity.AbstractDto


class AdvertisementResp() : AbstractDto() {

    var title: String? = null

    var body: String? = null

    var picture: String? = null

    var type: AdvertisementType? = null

    var status: AdvertisementStatus? = null

    fun getStringType():String{
        return type.toString()
    }

    fun getStringStatus():String{
        return status.toString()
    }

    fun getId():String{
        return id.toString()
    }

    fun getStringBody():String{
        return body.toString()
    }

    fun getStringTitle():String{
        return title.toString()
    }


}
