//package by.chekun.presentation.activities.add.chassis
//
//import by.chekun.repository.database.entity.car.chassis.WheelDriveTypeDto
//import by.chekun.multispinner.KeyPairBoolData
//import by.chekun.multispinner.SingleSpinner
//
//class WheelDriveSpinnerHolder(private val singleSpinner: SingleSpinner) {
//
//
//    fun initModelSpinnerHolderWithValues(values: Set<WheelDriveTypeDto>) {
//        initModelSpinner(values)
//    }
//
//
//    private fun initModelSpinner(values: Set<WheelDriveTypeDto>) {
//
//        val list: MutableList<KeyPairBoolData> = java.util.ArrayList()
//
//        values.forEach { value ->
//            val h = KeyPairBoolData()
//            h.id = value.id
//            h.name = value.wheelDriveType
//            h.isSelected = false
//            list.add(h)
//        }
//
//        singleSpinner.isColorseparation = true
//        singleSpinner.setItems(list, -1) { items ->
//            for (i in items!!.indices) {
//                if (items[i].isSelected) {
//
//                }
//            }
//        }
//    }
//
//}