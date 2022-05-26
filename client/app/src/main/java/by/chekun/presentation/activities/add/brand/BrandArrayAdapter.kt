package by.chekun.presentation.activities.add.brand

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import by.chekun.repository.database.entity.brand.BrandDto


class BrandArrayAdapter(
        context: Context,
        textViewResourceId: Int,
        private val values: Array<BrandDto>)
    : ArrayAdapter<BrandDto>(context, textViewResourceId, values) {


    override fun getCount(): Int {
        return values.size
    }

    override fun getItem(position: Int): BrandDto {
        return values[position]
    }

    override fun getItemId(position: Int): Long {
        return values[position].id
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.text = values[position].title
        return label
    }

    override fun getDropDownView(position: Int, convertView: View?,
                                 parent: ViewGroup): View {
        val label = super.getDropDownView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.text = values[position].title
        return label
    }

}