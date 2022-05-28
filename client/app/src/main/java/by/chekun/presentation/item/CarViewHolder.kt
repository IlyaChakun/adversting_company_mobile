package by.chekun.presentation.item

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.chekun.R
import by.chekun.databinding.CarItemBinding
import by.chekun.repository.database.entity.car.view.AdvertisementResp

class CarViewHolder(private val binding: CarItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    private var car: AdvertisementResp? = null
    private var listener: CarItemClickListener<AdvertisementResp>? = null
    private val itemDetail = View.OnClickListener { listener!!.openDetail(this.car!!) }

    var carItemImage: ImageView? = null

    init {
        this.carItemImage = binding.root.findViewById(R.id.carItemImage)
    }


    fun bind(car: AdvertisementResp, listener: CarItemClickListener<AdvertisementResp>) {
        this.car = car
        this.listener = listener
        this.setupItem()
    }

    private fun setupItem() {
        binding.car = car
        binding.root.setOnClickListener(itemDetail)
    }

}