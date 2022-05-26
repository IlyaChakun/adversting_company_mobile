package by.chekun.presentation.activities.add.interior

import by.chekun.repository.database.entity.car.interior.InteriorMaterialDto
import by.chekun.multispinner.KeyPairBoolData
import by.chekun.multispinner.SingleSpinner

class InteriorMaterialSpinnerHolder(private val singleSpinner: SingleSpinner) {


    fun initModelSpinnerHolderWithValues(values: Set<InteriorMaterialDto>) {
        initModelSpinner(values)
    }


    private fun initModelSpinner(values: Set<InteriorMaterialDto>) {

        val list: MutableList<KeyPairBoolData> = java.util.ArrayList()

        values.forEach { value ->
            val h = KeyPairBoolData()
            h.id = value.id
            h.name = value.interiorMaterial
            h.isSelected = false
            list.add(h)
        }

        singleSpinner.isColorseparation = true
        singleSpinner.setItems(list, -1) { items ->
            for (i in items!!.indices) {
                if (items[i].isSelected) {

                }
            }
        }
    }

}