package by.chekun.repository.database.entity.brand

import by.chekun.repository.database.entity.AbstractDto
import com.google.gson.annotations.SerializedName


class BodyTypeDto : AbstractDto {

    @SerializedName("bodyType")
    lateinit var bodyType: String

    constructor()

    constructor(bodyType: String) : super() {
        this.bodyType = bodyType
    }


    constructor(
            id: Long,
            bodyType: String
    ) : super(id) {
        this.bodyType = bodyType
    }
}