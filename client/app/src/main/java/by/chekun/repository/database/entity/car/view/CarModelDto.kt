package by.chekun.repository.database.entity.car.view

import by.chekun.repository.database.entity.AbstractDto


class CarModelDto : AbstractDto {

    lateinit var name: String

    constructor()

    constructor(id: Long, name: String) : super(id) {
        this.name = name
    }

}