package by.chekun.repository.database.entity.brand

import by.chekun.repository.database.entity.AbstractDto
import com.google.gson.annotations.SerializedName
import java.util.*


class BrandDto : AbstractDto {


    @SerializedName("title")
    lateinit var title: String

    var models: Set<ModelDto> = HashSet()

    constructor()

    constructor(title: String) : super() {
        this.title = title
    }

    constructor(id: Long, title: String) : super(id) {
        this.title = title
    }

    override fun toString(): String {
        return title
    }

}

