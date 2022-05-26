package by.chekun.presentation.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import by.chekun.databinding.CarItemBinding
import by.chekun.presentation.base.BaseAdapter
import by.chekun.presentation.item.CarItemClickListener
import by.chekun.presentation.item.CarViewHolder
import by.chekun.repository.database.entity.car.view.CarDto
import com.squareup.picasso.Picasso
import java.util.*


class CarAdapter(
        private val context: Context,
        private val cars: List<CarDto>,
        private val listener: CarItemClickListener<CarDto>) :
        BaseAdapter<CarViewHolder, CarDto,
                CarItemClickListener<CarDto>>(cars as MutableList<CarDto>, listener) {


    override fun getItemCount(): Int {
        return cars.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val binding: CarItemBinding = CarItemBinding.inflate(inflater, parent, false)
        return CarViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(cars[position], listener)

        val car = cars[position]
        if (car.picture != null) {
            val base64String = car.picture
            val imageBytes: ByteArray = Base64.getDecoder().decode(base64String)
            val bmp: Bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            holder.carItemImage?.setImageBitmap(bmp)
        } else {
            Picasso.get().load("https://sl2.d.umn.edu/och/PhotoGallery/no-image-available.jpg").into(holder.carItemImage)
        }
    }


}
