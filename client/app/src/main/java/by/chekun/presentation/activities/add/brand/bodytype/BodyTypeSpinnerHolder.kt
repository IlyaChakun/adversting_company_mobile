package by.chekun.presentation.activities.add.brand.bodytype

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import by.chekun.repository.database.entity.brand.BodyTypeDto
import by.chekun.utils.BODY_TYPE_SPINNER_KEY

class BodyTypeSpinnerHolder(private val context: Context,
                            private val addActivitySpinners: MutableMap<String, Spinner>) {


    fun initModelSpinnerHolderWithValues(models: Set<BodyTypeDto>) {
        initModelSpinner(addActivitySpinners[BODY_TYPE_SPINNER_KEY]!!, context, models)
    }


    private fun initModelSpinner(spinner: Spinner, context: Context, models: Set<BodyTypeDto>) {

        val newList: MutableList<BodyTypeDto> = ArrayList()
        newList.addAll(models)

        val bodyTypes = newList.toTypedArray()
        val adapter = createModelAdapter(context, bodyTypes)
        BodyTypeSpinnerListener(adapter, spinner)
    }


    private fun createModelAdapter(context: Context, values: Array<BodyTypeDto>): ArrayAdapter<BodyTypeDto> {

        return object : ArrayAdapter<BodyTypeDto>(context, android.R.layout.simple_spinner_dropdown_item, values) {


            override fun getCount(): Int {
                return values.size
            }

            override fun getItem(position: Int): BodyTypeDto {
                return values[position]
            }

            override fun getItemId(position: Int): Long {
                return values[position].id
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val label = super.getView(position, convertView, parent) as TextView
                label.setTextColor(Color.BLACK)
                label.text = values[position].bodyType
                return label
            }

            override fun getDropDownView(position: Int, convertView: View?,
                                         parent: ViewGroup): View {
                val label = super.getDropDownView(position, convertView, parent) as TextView
                label.setTextColor(Color.BLACK)
                label.text = values[position].bodyType
                return label
            }

        }
    }
}