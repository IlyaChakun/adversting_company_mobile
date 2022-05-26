package by.chekun.presentation.activities.add.brand.generation

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import by.chekun.presentation.activities.add.brand.bodytype.BodyTypeSpinnerHolder
import by.chekun.repository.database.entity.brand.GenerationDto

class GenerationSpinnerListener(dataAdapter: ArrayAdapter<GenerationDto>,
                                spinner: Spinner,
                                private val bodyTypeSpinnerHolder: BodyTypeSpinnerHolder
) : AdapterView.OnItemSelectedListener {

    init {
        spinner.onItemSelectedListener = this
        spinner.adapter = dataAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (position != 0) {
            val generation = parent.selectedItem as GenerationDto
            bodyTypeSpinnerHolder.initModelSpinnerHolderWithValues(generation.bodyTypes)
        } else {
            bodyTypeSpinnerHolder.initModelSpinnerHolderWithValues(emptySet())
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}