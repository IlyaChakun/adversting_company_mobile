package by.chekun.presentation.activities.add.equipment

import by.chekun.repository.database.entity.car.equipment.ColorDto
import by.chekun.multispinner.KeyPairBoolData
import by.chekun.multispinner.SingleSpinner

class ColorSpinnerHolder(private val singleSpinner: SingleSpinner) {


    fun initModelSpinnerHolderWithValues(values: Set<ColorDto>) {
        initModelSpinner(values)
    }


    private fun initModelSpinner(values: Set<ColorDto>) {

        val list: MutableList<KeyPairBoolData> = java.util.ArrayList()

        values.forEach { value ->
            val h = KeyPairBoolData()
            h.id = value.id
            h.name = value.color
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