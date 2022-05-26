package by.chekun.repository.database.entity.brand

import by.chekun.repository.database.entity.AbstractDto
import com.google.gson.annotations.SerializedName
import java.util.*

class ModelDto : AbstractDto {

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("releaseYears")
    var releaseYears: Set<ReleaseYearDto> = HashSet()


    constructor()

    constructor(name: String) : super() {
        this.name = name
    }

    constructor(id: Long, name: String) : super(id) {
        this.name = name
    }

}