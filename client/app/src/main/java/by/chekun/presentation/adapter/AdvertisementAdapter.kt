package by.chekun.presentation.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import by.chekun.databinding.CarItemBinding
import by.chekun.presentation.base.BaseAdapter
import by.chekun.presentation.item.CarItemClickListener
import by.chekun.presentation.item.AdvertisementViewHolder
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import com.squareup.picasso.Picasso
import java.util.*


class AdvertisementAdapter(
        private val context: Context,
        private val advertisementItems: List<AdvertisementResp>,
        private val listener: CarItemClickListener<AdvertisementResp>) :
        BaseAdapter<AdvertisementViewHolder, AdvertisementResp,
                CarItemClickListener<AdvertisementResp>>(advertisementItems as MutableList<AdvertisementResp>, listener) {


    override fun getItemCount(): Int {
        return advertisementItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisementViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val binding: CarItemBinding = CarItemBinding.inflate(inflater, parent, false)
        return AdvertisementViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: AdvertisementViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(advertisementItems[position], listener)

        val car = advertisementItems[position]
//        if (car.picture != null) {
//            val base64String = car.picture
//            val imageBytes: ByteArray = Base64.getDecoder().decode(base64String)
//            val bmp: Bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//
//            holder.carItemImage?.setImageBitmap(bmp)
//        } else {
            Picasso.get().load("https://sl2.d.umn.edu/och/PhotoGallery/no-image-available.jpg").into(holder.carItemImage)
        //}
    }


}
