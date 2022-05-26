package by.chekun.repository.database.entity.car.view

import by.chekun.repository.database.entity.AbstractDto


class CarBrandDto : AbstractDto {

    lateinit var title: String

    constructor()

    constructor(id: Long, title: String) : super(id) {
        this.title = title
    }
}