package by.chekun.repository.database.entity.car.interior

import by.chekun.repository.database.entity.AbstractDto


class InteriorColorDto : AbstractDto {

    lateinit var interiorColor: String

    constructor()

    constructor(interiorColor: String) : super() {
        this.interiorColor = interiorColor
    }

    constructor(id: Long, interiorColor: String) : super(id) {
        this.interiorColor = interiorColor
    }

}