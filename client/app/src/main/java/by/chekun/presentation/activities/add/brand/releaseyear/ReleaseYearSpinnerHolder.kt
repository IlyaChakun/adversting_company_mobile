package by.chekun.presentation.activities.add.brand.releaseyear

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import by.chekun.presentation.activities.add.brand.generation.GenerationSpinnerHolder
import by.chekun.repository.database.entity.brand.ReleaseYearDto
import by.chekun.utils.RELEASE_YEAR_SPINNER_KEY

class ReleaseYearSpinnerHolder(private val context: Context,
                               private val addActivitySpinners: MutableMap<String, Spinner>) {

    private var modelSpinnerListener: ReleaseYearSpinnerListener? = null

    fun initModelSpinnerHolderWithValues(years: Set<ReleaseYearDto>) {
        initModelSpinner(addActivitySpinners[RELEASE_YEAR_SPINNER_KEY]!!, context, years)
    }


    private fun initModelSpinner(spinner: Spinner, context: Context, years: Set<ReleaseYearDto>) {

        val newList: MutableList<ReleaseYearDto> = ArrayList()
        newList.addAll(years)

        val yearsArray = newList.toTypedArray()
        val adapter = createAdapter(context, yearsArray)

        val generationSpinnerHolder = GenerationSpinnerHolder(context, addActivitySpinners)
        modelSpinnerListener = ReleaseYearSpinnerListener(adapter, spinner, generationSpinnerHolder)
    }


    private fun createAdapter(context: Context, values: Array<ReleaseYearDto>): ArrayAdapter<ReleaseYearDto> {

        return object : ArrayAdapter<ReleaseYearDto>(context, android.R.layout.simple_spinner_dropdown_item, values) {


            override fun getCount(): Int {
                return values.size
            }

            override fun getItem(position: Int): ReleaseYearDto {
                return values[position]
            }

            override fun getItemId(position: Int): Long {
                return values[position].id
            }


            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val label = super.getView(position, convertView, parent) as TextView
                return if (position != 0) {
                    label.setTextColor(Color.BLACK)
                    label.text = values[position].releaseYear.toString()
                    label
                } else {
                    label.text = ""
                    label
                }
            }

            override fun getDropDownView(position: Int, convertView: View?,
                                         parent: ViewGroup): View {
                val label = super.getDropDownView(position, convertView, parent) as TextView
                label.setTextColor(Color.BLACK)
                label.text = values[position].releaseYear.toString()
                return label
            }

        }
    }
}