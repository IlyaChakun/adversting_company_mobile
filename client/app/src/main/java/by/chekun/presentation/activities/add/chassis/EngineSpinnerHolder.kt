package by.chekun.presentation.activities.add.chassis

import by.chekun.repository.database.entity.car.chassis.EngineTypeDto
//import by.chekun.multispinner.KeyPairBoolData
//import by.chekun.multispinner.SingleSpinner
//
//class EngineSpinnerHolder(private val singleSpinner: SingleSpinner) {
//
//
//    fun initModelSpinnerHolderWithValues(engineTypes: Set<EngineTypeDto>) {
//        initModelSpinner(engineTypes)
//    }
//
//
//    private fun initModelSpinner(engineTypes: Set<EngineTypeDto>) {
//
//        val list: MutableList<KeyPairBoolData> = java.util.ArrayList()
//
//        engineTypes.forEach { value ->
//            val h = KeyPairBoolData()
//            h.id = value.id
//            h.name = value.engineType
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