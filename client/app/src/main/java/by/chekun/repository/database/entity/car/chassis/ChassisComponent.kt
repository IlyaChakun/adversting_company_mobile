package by.chekun.repository.database.entity.car.chassis

data class ChassisComponent(

        var engineTypes: List<EngineTypeDto>,

        var transmissionTypes: List<TransmissionTypeDto>,

        var wheelDriveTypes: List<WheelDriveTypeDto>

)