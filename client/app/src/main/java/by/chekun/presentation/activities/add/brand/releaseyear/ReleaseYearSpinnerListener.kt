package by.chekun.presentation.activities.add.brand.releaseyear

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import by.chekun.presentation.activities.add.brand.generation.GenerationSpinnerHolder
import by.chekun.repository.database.entity.brand.ReleaseYearDto

class ReleaseYearSpinnerListener(dataAdapter: ArrayAdapter<ReleaseYearDto>,
                                 spinner: Spinner,
                                 private val generationSpinnerHolder: GenerationSpinnerHolder
) : AdapterView.OnItemSelectedListener {


    init {
        spinner.onItemSelectedListener = this
        spinner.adapter = dataAdapter
    }


    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (position != 0) {
            val releaseYear = parent.selectedItem as ReleaseYearDto
            generationSpinnerHolder.initSpinnerHolderWithValues(releaseYear.generations)
        } else {
            generationSpinnerHolder.initSpinnerHolderWithValues(emptySet())
        }
    }


    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}