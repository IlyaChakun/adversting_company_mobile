package by.chekun.presentation.activities.add.brand.model

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import by.chekun.presentation.activities.add.brand.releaseyear.ReleaseYearSpinnerHolder
import by.chekun.repository.database.entity.brand.ModelDto

class ModelSpinnerListener(dataAdapter: ArrayAdapter<ModelDto>,
                           spinner: Spinner,
                           private val releaseYearSpinnerHolder: ReleaseYearSpinnerHolder
) : AdapterView.OnItemSelectedListener {


    init {
        spinner.onItemSelectedListener = this
        spinner.adapter = dataAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (position != 0) {
            val model = parent.selectedItem as ModelDto
            releaseYearSpinnerHolder.initModelSpinnerHolderWithValues(model.releaseYears)
        } else {
            releaseYearSpinnerHolder.initModelSpinnerHolderWithValues(emptySet())
        }
    }


    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}