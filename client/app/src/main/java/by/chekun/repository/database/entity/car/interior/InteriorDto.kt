package by.chekun.repository.database.entity.car.interior

import by.chekun.repository.database.entity.AbstractDto


class InteriorDto : AbstractDto {

    lateinit var interior: String

    constructor()

    constructor(id: Long, interior: String) : super(id) {
        this.interior = interior
    }

}

