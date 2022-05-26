package by.chekun.presentation.activities.add.brand.model

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import by.chekun.presentation.activities.add.brand.releaseyear.ReleaseYearSpinnerHolder
import by.chekun.repository.database.entity.brand.ModelDto
import by.chekun.utils.MODEL_SPINNER_KEY

class ModelSpinnerHolder(private val context: Context,
                         private val addActivitySpinners: MutableMap<String, Spinner>) {


    fun initModelSpinnerHolderWithValues(models: Set<ModelDto>) {
        initModelSpinner(addActivitySpinners[MODEL_SPINNER_KEY]!!, context, models)
    }


    private fun initModelSpinner(spinner: Spinner, context: Context, models: Set<ModelDto>) {

        val newList: MutableList<ModelDto> = ArrayList()
        newList.add(ModelDto(""))
        newList.addAll(models)

        val modelsArray = newList.toTypedArray()
        val adapter = createModelAdapter(context, modelsArray)

        val releaseYearSpinnerHolder = ReleaseYearSpinnerHolder(context, addActivitySpinners)
        ModelSpinnerListener(adapter, spinner, releaseYearSpinnerHolder)
    }


    private fun createModelAdapter(context: Context, values: Array<ModelDto>): ArrayAdapter<ModelDto> {

        return object : ArrayAdapter<ModelDto>(context, android.R.layout.simple_spinner_dropdown_item, values) {


            override fun getCount(): Int {
                return values.size
            }

            override fun getItem(position: Int): ModelDto {
                return values[position]
            }

            override fun getItemId(position: Int): Long {
                return values[position].id
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val label = super.getView(position, convertView, parent) as TextView
                label.setTextColor(Color.BLACK)
                label.text = values[position].name
                return label
            }

            override fun getDropDownView(position: Int, convertView: View?,
                                         parent: ViewGroup): View {
                val label = super.getDropDownView(position, convertView, parent) as TextView
                label.setTextColor(Color.BLACK)
                label.text = values[position].name
                return label
            }

        }
    }
}