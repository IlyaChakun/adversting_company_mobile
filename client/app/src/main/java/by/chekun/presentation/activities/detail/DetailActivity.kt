package by.chekun.presentation.activities.detail


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.LeadingMarginSpan
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import by.chekun.R
import by.chekun.databinding.CarDetailBinding
import by.chekun.di.component.ViewModelComponent
import by.chekun.domain.SingleCarViewModel
import by.chekun.presentation.base.BaseActivity
import by.chekun.repository.database.entity.car.view.AdvertisementResp
import java.util.*
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    var viewModel: SingleCarViewModel? = null
        @Inject set

    private lateinit var binding: CarDetailBinding
    private var carId: Long = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.title = "Подробный просмотр"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        initViewModel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViewModel() {
        carId = intent.getLongExtra(getString(R.string.EXTRAS_ID), 0)
        viewModel?.getItem(carId)
        viewModel?.getLiveDataItem()?.observe(this, Observer { it?.let { initDataBinding(it) } })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initDataBinding(car: AdvertisementResp) {
        binding.car = car
     //   initActionBar("${car.model} ${car.generation}")
        initOrderedList(car)
        initCarImage(car)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initCarImage(car: AdvertisementResp) {
//        if (car.picture != null) {
//            val base64String = car.picture
//            // Receiving side
//
//            // Receiving side
//            val imageBytes: ByteArray = Base64.getDecoder().decode(base64String)
//            val bmp: Bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//            val image: ImageView = findViewById<View>(R.id.detailCarImage) as ImageView
//
//            image.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.width,
//                    image.height, false))
//        }
    }

    private fun initOrderedList(car: AdvertisementResp) {
        val safetiesLabel: TextView = findViewById(R.id.labelSafeties)
        val safeties: TextView = findViewById(R.id.safeties)

        val interiorLabel: TextView = findViewById(R.id.labelInterior)
        val interior: TextView = findViewById(R.id.interior)

//        if (car.safeties.isEmpty()) {
//            safetiesLabel.visibility = View.INVISIBLE;
//            safeties.visibility = View.INVISIBLE;
//        } else {
//            val content = SpannableStringBuilder()
//            var number = 1
//            for (safetyDto in car.safeties) {
//                val contentStart: Int = content.length
//                val leadingString = "$number."
//                content.append(leadingString)
//                content.append(safetyDto.safety)
//                content.append("\n")
//                val contentEnd: Int = content.length
//                content.setSpan(
//                        LeadingMarginSpan.Standard(0, 66),
//                        contentStart,
//                        contentEnd,
//                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
//                )
//                number++
//            }
//            safeties.text = content.toString()
//        }
//
//        if (car.interior.isEmpty()) {
//            interiorLabel.visibility = View.INVISIBLE;
//            interior.visibility = View.INVISIBLE;
//        } else {
//            val content = SpannableStringBuilder()
//            var number = 1
//            for (interiorDto in car.interior) {
//                val contentStart: Int = content.length
//                val leadingString = "$number."
//                content.append(leadingString)
//                content.append(interiorDto.interior)
//                content.append("\n")
//                val contentEnd: Int = content.length
//                content.setSpan(
//                        LeadingMarginSpan.Standard(0, 66),
//                        contentStart,
//                        contentEnd,
//                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
//                )
//                number++
//            }
//            interior.text = content.toString()
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context, id: Long): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(context.getString(R.string.EXTRAS_ID), id)
            return intent
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun initActionBar(title: String) {
        Objects.requireNonNull(supportActionBar)?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
