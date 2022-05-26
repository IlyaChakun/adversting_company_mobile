package by.chekun.repository.database.entity.brand

import by.chekun.repository.database.entity.AbstractDto
import com.google.gson.annotations.SerializedName
import java.util.*


class ReleaseYearDto : AbstractDto {

    @SerializedName("releaseYear")
    var releaseYear: Int = 0

    @SerializedName("generations")
    var generations: Set<GenerationDto> = HashSet()

    constructor()

    constructor(releaseYear: Int) : super() {
        this.releaseYear = releaseYear
    }

    constructor(id: Long, releaseYear: Int, generations: Set<GenerationDto>) : super(id) {
        this.releaseYear = releaseYear
        this.generations = generations
    }


}