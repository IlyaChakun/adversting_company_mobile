package by.chekun.presentation.item

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import by.chekun.R
import by.chekun.databinding.CarItemAdminBinding
import by.chekun.repository.database.entity.User
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import by.chekun.repository.database.entity.user.AccessTokenDTO
import by.chekun.repository.database.entity.user.LoginRequest
import by.chekun.repository.database.entity.user.UserResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class PendingAdvertisementViewHolder(private val binding: CarItemAdminBinding)
    : RecyclerView.ViewHolder(binding.root) {

    private var advertisement: AdvertisementResp? = null
    private var listener: CarItemClickListener<AdvertisementResp>? = null
    private val itemDetail = View.OnClickListener { listener!!.openDetail(this.advertisement!!) }

    var carItemImage: ImageView? = null
    var publishButton: Button? = null

    init {
        this.carItemImage = binding.root.findViewById(R.id.picture)
        this.publishButton = binding.root.findViewById(R.id.car_save_button)
    }


    fun bind(car: AdvertisementResp, listener: CarItemClickListener<AdvertisementResp>) {
        this.advertisement = car
        this.listener = listener
        this.setupItem()
    }

    private fun setupItem() {
        binding.advertisement = advertisement
        binding.root.setOnClickListener(itemDetail)
    }

}