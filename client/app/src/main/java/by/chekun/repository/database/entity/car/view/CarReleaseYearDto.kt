package by.chekun.repository.database.entity.car.view

import by.chekun.repository.database.entity.AbstractDto


class CarReleaseYearDto : AbstractDto {

    var releaseYear: Int = 0

    constructor()

    constructor(id: Long, releaseYear: Int) : super(id) {
        this.releaseYear = releaseYear
    }

    fun getReleaseYearString(): String {
        return releaseYear.toString()
    }
}