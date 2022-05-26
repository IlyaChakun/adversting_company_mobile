package by.chekun.presentation.activities.add.equipment

import by.chekun.repository.database.entity.car.equipment.SafetyDto
import by.chekun.multispinner.KeyPairBoolData
import by.chekun.multispinner.MultiSpinnerSearch

class SafetiesMultipleSpinnerHolder(private val safetiesMultipleSpinner: MultiSpinnerSearch) {


    fun initModelSpinnerHolderWithValues(values: Set<SafetyDto>) {
        initModelSpinner(values)
    }


    private fun initModelSpinner(safeties: Set<SafetyDto>) {

        val list: MutableList<KeyPairBoolData> = java.util.ArrayList()

        safeties.forEach { safety ->
            val h = KeyPairBoolData()
            h.id = safety.id
            h.name = safety.safety
            h.isSelected = false
            list.add(h)
        }

        safetiesMultipleSpinner.setEmptyTitle("Not Data Found!")
        safetiesMultipleSpinner.setSearchHint("Find Data")

        safetiesMultipleSpinner.setItems(list, -1) { items ->
            for (i in items.indices) {
                if (items[i].isSelected) {
                }
            }
        }

    }

}