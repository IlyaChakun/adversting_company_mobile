package by.chekun.repository.database.entity.car.interior

data class InteriorComponent(

        var interiorColors: List<InteriorColorDto>,

        var interior: List<InteriorDto>,

        var interiorMaterials: List<InteriorMaterialDto>

)