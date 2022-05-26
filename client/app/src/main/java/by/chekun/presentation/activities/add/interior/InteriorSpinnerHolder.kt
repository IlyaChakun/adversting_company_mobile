package by.chekun.presentation.activities.add.interior

import by.chekun.repository.database.entity.car.interior.InteriorDto
import by.chekun.multispinner.KeyPairBoolData
import by.chekun.multispinner.MultiSpinnerSearch


class InteriorSpinnerHolder(private val multiSpinnerSearch: MultiSpinnerSearch) {


    fun initModelSpinnerHolderWithValues(values: Set<InteriorDto>) {
        initModelSpinner(values)
    }


    private fun initModelSpinner(values: Set<InteriorDto>) {

        val list: MutableList<KeyPairBoolData> = java.util.ArrayList()

        values.forEach { value ->
            val h = KeyPairBoolData()
            h.id = value.id
            h.name = value.interior
            h.isSelected = false
            list.add(h)
        }

        multiSpinnerSearch.setEmptyTitle("Not Data Found!")
        multiSpinnerSearch.setSearchHint("Find Data")

        multiSpinnerSearch.setItems(list, -1) { items ->
            for (i in items.indices) {
                if (items[i].isSelected) {
                }
            }
        }
    }

}