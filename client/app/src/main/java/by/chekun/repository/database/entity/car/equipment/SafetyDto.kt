package by.chekun.repository.database.entity.car.equipment

import by.chekun.repository.database.entity.AbstractDto


class SafetyDto : AbstractDto {

    lateinit var safety: String

    constructor()

    constructor(id: Long, safety: String) : super(id) {
        this.safety = safety
    }

}