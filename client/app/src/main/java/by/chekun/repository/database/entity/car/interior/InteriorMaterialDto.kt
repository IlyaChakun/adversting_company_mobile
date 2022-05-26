package by.chekun.repository.database.entity.car.interior

import by.chekun.repository.database.entity.AbstractDto


class InteriorMaterialDto : AbstractDto {

    lateinit var interiorMaterial: String

    constructor()

    constructor(interiorMaterial: String) : super() {
        this.interiorMaterial = interiorMaterial
    }

    constructor(id: Long, interiorMaterial: String) : super(id) {
        this.interiorMaterial = interiorMaterial
    }

}
