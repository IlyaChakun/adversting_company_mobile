package by.chekun.repository.database.entity.brand

import by.chekun.repository.database.entity.AbstractDto
import com.google.gson.annotations.SerializedName
import java.util.*


class GenerationDto : AbstractDto {

    @SerializedName("generation")
    lateinit var generation: String

    @SerializedName("startYear")
    var startYear: Int = 0

    @SerializedName("finishYear")
    var finishYear: Int = 0

    @SerializedName("bodyTypes")
    var bodyTypes: Set<BodyTypeDto> = HashSet()

    constructor()

    constructor(generation: String) : super() {
        this.generation = generation
    }

    constructor(
            id: Long,
            generation: String,
            bodyTypes: Set<BodyTypeDto>
    ) : super(id) {
        this.generation = generation
        this.bodyTypes = bodyTypes
    }


}