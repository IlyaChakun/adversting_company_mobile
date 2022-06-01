package by.chekun.presentation.adapter

import android.content.Context
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import by.chekun.R
import by.chekun.databinding.CarItemAdminBinding
import by.chekun.presentation.base.BaseAdapter
import by.chekun.presentation.item.CarItemClickListener
import by.chekun.presentation.item.PendingAdvertisementViewHolder
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import com.squareup.picasso.Picasso
import java.util.*


class PendingAdvertisementAdapter (
    private val context: Context,
    private val advertisementItems: List<AdvertisementResp>,
    private val listener: CarItemClickListener<AdvertisementResp>) :
    BaseAdapter<PendingAdvertisementViewHolder, AdvertisementResp,
            CarItemClickListener<AdvertisementResp>>(advertisementItems as MutableList<AdvertisementResp>, listener) {

        override fun getItemCount(): Int {
        return advertisementItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingAdvertisementViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val binding: CarItemAdminBinding = CarItemAdminBinding.inflate(inflater, parent, false)
        return PendingAdvertisementViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PendingAdvertisementViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(advertisementItems[position], listener)

        val car = advertisementItems[position]
        if (car.picture != null) {
            val base64String = car.picture
            val decoder: Base64.Decoder = Base64.getDecoder()
            val decoded = String(decoder.decode(base64String))
            holder.carItemImage?.setImageURI(Uri.parse(decoded));
/*            val imageBytes: ByteArray = Base64.getDecoder().decode(base64String)
            val bmp: Bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            holder.carItemImage?.setImageBitmap(bmp)*/
        } else {
            Picasso.get().load("https://image.shutterstock.com/z/stock-photo-a-picture-of-the-beautiful-view-of-birds-1836263689.jpg").into(holder.carItemImage)
        }
    }


}
