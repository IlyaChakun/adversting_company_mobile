package by.chekun.presentation.activities.add.brand

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import by.chekun.presentation.activities.add.brand.model.ModelSpinnerHolder
import by.chekun.repository.database.entity.brand.BrandDto


class BrandSpinnerListener(
        dataAdapter: ArrayAdapter<BrandDto>,
        spinner: Spinner,
        private val modelSpinner: ModelSpinnerHolder
) : OnItemSelectedListener {

    init {
        spinner.onItemSelectedListener = this
        spinner.adapter = dataAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (position != 0) {
            val brand = parent.selectedItem as BrandDto
            modelSpinner.initModelSpinnerHolderWithValues(brand.models)
        } else {
            modelSpinner.initModelSpinnerHolderWithValues(emptySet())
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        println("onNothingSelected WORKS!")
    }

}