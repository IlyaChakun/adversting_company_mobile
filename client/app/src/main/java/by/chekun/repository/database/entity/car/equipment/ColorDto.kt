package by.chekun.repository.database.entity.car.equipment

import by.chekun.repository.database.entity.AbstractDto


class ColorDto : AbstractDto {

    lateinit var color: String

    constructor()

    constructor(color: String) : super() {
        this.color = color
    }

    constructor(id: Long, color: String) : super(id) {
        this.color = color
    }

}