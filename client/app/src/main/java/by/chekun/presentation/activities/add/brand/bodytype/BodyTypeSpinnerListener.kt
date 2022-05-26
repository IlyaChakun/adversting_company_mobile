package by.chekun.presentation.activities.add.brand.bodytype

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import by.chekun.repository.database.entity.brand.BodyTypeDto

class BodyTypeSpinnerListener(dataAdapter: ArrayAdapter<BodyTypeDto>,
                              spinner: Spinner
) : AdapterView.OnItemSelectedListener {

    init {
        spinner.onItemSelectedListener = this
        spinner.adapter = dataAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (position != 0) {
            val bodyType = parent.selectedItem as BodyTypeDto
        }
    }


    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}