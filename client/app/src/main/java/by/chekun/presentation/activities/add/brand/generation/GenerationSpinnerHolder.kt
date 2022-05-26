package by.chekun.presentation.activities.add.brand.generation

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import by.chekun.presentation.activities.add.brand.bodytype.BodyTypeSpinnerHolder
import by.chekun.repository.database.entity.brand.GenerationDto
import by.chekun.utils.GENERATION_SPINNER_KEY

class GenerationSpinnerHolder(private val context: Context,
                              private val addActivitySpinners: MutableMap<String, Spinner>) {


    fun initSpinnerHolderWithValues(values: Set<GenerationDto>) {
        initModelSpinner(addActivitySpinners[GENERATION_SPINNER_KEY]!!, context, values)
    }

    private fun initModelSpinner(spinner: Spinner, context: Context, values: Set<GenerationDto>) {

        val newList: MutableList<GenerationDto> = ArrayList()
        newList.add(GenerationDto(""))
        newList.addAll(values)

        val modelsArray = newList.toTypedArray()
        val adapter = createModelAdapter(context, modelsArray)

        val bodyTypeSpinnerHolder = BodyTypeSpinnerHolder(context, addActivitySpinners)
        GenerationSpinnerListener(adapter, spinner, bodyTypeSpinnerHolder)
    }

    private fun createModelAdapter(context: Context, values: Array<GenerationDto>): ArrayAdapter<GenerationDto> {

        return object : ArrayAdapter<GenerationDto>(context, android.R.layout.simple_spinner_dropdown_item, values) {

            override fun getCount(): Int {
                return values.size
            }

            override fun getItem(position: Int): GenerationDto {
                return values[position]
            }

            override fun getItemId(position: Int): Long {
                return values[position].id
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val label = super.getView(position, convertView, parent) as TextView
                label.setTextColor(Color.BLACK)
                label.text = values[position].generation
                return label
            }

            override fun getDropDownView(position: Int, convertView: View?,
                                         parent: ViewGroup): View {
                val label = super.getDropDownView(position, convertView, parent) as TextView
                label.setTextColor(Color.BLACK)
                label.text = values[position].generation
                return label
            }

        }
    }
}