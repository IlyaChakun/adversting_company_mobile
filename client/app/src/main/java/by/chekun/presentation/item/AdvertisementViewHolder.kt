package by.chekun.presentation.item

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.chekun.R
import by.chekun.databinding.CarItemBinding
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import kotlinx.android.synthetic.main.item_admin_advertisement.view.*

class AdvertisementViewHolder(private val binding: CarItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    private var advertisement: AdvertisementResp? = null
    private var listener: CarItemClickListener<AdvertisementResp>? = null
    private val itemDetail = View.OnClickListener { listener!!.openDetail(this.advertisement!!) }

    var carItemImage: ImageView? = null

    init {
        this.carItemImage = binding.root.findViewById(R.id.picture)
    }


    fun bind(car: AdvertisementResp, listener: CarItemClickListener<AdvertisementResp>) {
        this.advertisement = car
        this.listener = listener
        this.setupItem()
    }

    private fun setupItem() {
        binding.advertisement = advertisement
        binding.root.setOnClickListener(itemDetail)
        if( binding.root.adv_publish != null ) {
            binding.root.adv_publish.setOnClickListener(itemDetail)
        }

        if( binding.root.adv_denied != null ) {
            binding.root.adv_denied.setOnClickListener(itemDetail)
        }
    }

}