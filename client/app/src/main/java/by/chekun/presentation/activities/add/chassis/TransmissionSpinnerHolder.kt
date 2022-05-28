//package by.chekun.presentation.activities.add.chassis
//
//import by.chekun.repository.database.entity.car.chassis.TransmissionTypeDto
//import by.chekun.multispinner.KeyPairBoolData
//import by.chekun.multispinner.SingleSpinner
//
//class TransmissionSpinnerHolder(private val singleSpinner: SingleSpinner) {
//
//
//    fun initModelSpinnerHolderWithValues(values: Set<TransmissionTypeDto>) {
//        initModelSpinner(values)
//    }
//
//
//    private fun initModelSpinner(values: Set<TransmissionTypeDto>) {
//
//        val list: MutableList<KeyPairBoolData> = java.util.ArrayList()
//
//        values.forEach { value ->
//            val h = KeyPairBoolData()
//            h.id = value.id
//            h.name = value.transmissionType
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