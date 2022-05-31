package by.chekun.presentation.activities.detail


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.net.Uri.parse
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import by.chekun.R
import by.chekun.databinding.CarDetailBinding
import by.chekun.di.component.ViewModelComponent
import by.chekun.domain.SingleAdvertisementViewModel
import by.chekun.presentation.activities.main.MainActivity
import by.chekun.presentation.base.BaseActivity
import by.chekun.repository.database.entity.advertisement.AdvertisementRatingRequest
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import by.chekun.repository.database.entity.user.TextResp
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URI
import java.net.URI.*
import java.util.*
import java.util.logging.Level.parse
import javax.inject.Inject


class DetailActivity : BaseActivity() {

    var viewModel: SingleAdvertisementViewModel? = null
        @Inject set

    private lateinit var binding: CarDetailBinding

    var rateNumber: Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.title = "Подробный просмотр"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        initViewModel()
    }

    private fun initViewModel() {
        //advId = intent.getLongExtra(getString(R.string.EXTRAS_ID), 2)
        val arguments = intent.extras
        val advId = arguments?.get("advId")
        //viewModel?.getItem(advId as Long)
        //viewModel?.getLiveDataItem()?.observe(this, Observer { it?.let { initDataBinding(it) } })
        viewModel?.getItem(advId as Long)?.enqueue(object : Callback<AdvertisementResp?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<AdvertisementResp?>,
                response: Response<AdvertisementResp?>
            ) {
                val advertisement = response.body()
                if (advertisement != null) {
                    initDataBinding(advertisement)
                }
            }

            override fun onFailure(call: Call<AdvertisementResp?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(applicationContext, "Реклама не найдена!", Toast.LENGTH_SHORT).show()

            }
        })


        //showMainActivity()

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initDataBinding(advertisement: AdvertisementResp) {
        binding.advertisement = advertisement
        //   initActionBar("${car.model} ${car.generation}")
        initOrderedList(advertisement)
        initCarImage(advertisement)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initCarImage(advertisement: AdvertisementResp) {
        if (advertisement.picture != null) {
            val base64String = advertisement.picture
            // Receiving side

            // Receiving side
            val decoder: Base64.Decoder = Base64.getDecoder()
            val decoded = String(decoder.decode(base64String))
            val image: ImageView = findViewById<View>(R.id.picture) as ImageView

            image.setImageURI(Uri.parse(decoded));
        }
    }

    private fun initOrderedList(advertisement: AdvertisementResp) {
/*        val safetiesLabel: TextView = findViewById(R.id.labelSafeties)
        val safeties: TextView = findViewById(R.id.safeties)

        val interiorLabel: TextView = findViewById(R.id.labelInterior)
        val interior: TextView = findViewById(R.id.interior)

        if (advertisement.safeties.isEmpty()) {
            safetiesLabel.visibility = View.INVISIBLE;
            safeties.visibility = View.INVISIBLE;
        } else {
            val content = SpannableStringBuilder()
            var number = 1
            for (safetyDto in advertisement.safeties) {
                val contentStart: Int = content.length
                val leadingString = "$number."
                content.append(leadingString)
                content.append(safetyDto.safety)
                content.append("\n")
                val contentEnd: Int = content.length
                content.setSpan(
                        LeadingMarginSpan.Standard(0, 66),
                        contentStart,
                        contentEnd,
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
                number++
            }
            safeties.text = content.toString()
        }

        if (advertisement.interior.isEmpty()) {
            interiorLabel.visibility = View.INVISIBLE;
            interior.visibility = View.INVISIBLE;
        } else {
            val content = SpannableStringBuilder()
            var number = 1
            for (interiorDto in advertisement.interior) {
                val contentStart: Int = content.length
                val leadingString = "$number."
                content.append(leadingString)
                content.append(interiorDto.interior)
                content.append("\n")
                val contentEnd: Int = content.length
                content.setSpan(
                        LeadingMarginSpan.Standard(0, 66),
                        contentStart,
                        contentEnd,
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
                number++
            }
            interior.text = content.toString()
        }*/
    }

    @SuppressLint("ResourceType")
    fun clickRateNumber(view: View?) {
        val button1: Button = findViewById(R.id.rateButton1)
        val button2: Button = findViewById(R.id.rateButton2)
        val button3: Button = findViewById(R.id.rateButton3)
        val button4: Button = findViewById(R.id.rateButton4)
        val button5: Button = findViewById(R.id.rateButton5)
        changeColor(button1, Color.RED)
        changeColor(button2, Color.RED)
        changeColor(button3, Color.RED)
        changeColor(button4, Color.RED)
        changeColor(button5, Color.RED)
        when (view?.getId()) {
            R.id.rateButton1 -> {
                rateNumber = 1
                changeColor(button1, Color.DKGRAY);
            }
            R.id.rateButton2 -> {
                rateNumber = 2
                changeColor(button2, Color.DKGRAY);
            }
            R.id.rateButton3 -> {
                rateNumber = 3
                changeColor(button3, Color.DKGRAY);
            }
            R.id.rateButton4 -> {
                rateNumber = 4
                changeColor(button4, Color.DKGRAY);
            }
            R.id.rateButton5 -> {
                rateNumber = 5
                changeColor(button5, Color.DKGRAY);
            }
        }
    }

    fun clickComment(view: View?) {
        val reviewMessage = findViewById<EditText>(R.id.txt_comment).text.toString()
        val req = AdvertisementRatingRequest()
        val arguments = intent.extras
        val advId = arguments?.get("advId")

        req.reviewMessage = reviewMessage
        req.rating = rateNumber
        req.advertisementId = advId as Long

        viewModel?.addRating(req)?.enqueue(object : Callback<TextResp?> {
            override fun onResponse(
                call: Call<TextResp?>,
                response: Response<TextResp?>
            ) {
                Toast.makeText(applicationContext, "Комментарий добавлен!", Toast.LENGTH_SHORT).show()
                showMainActivity()
            }

            override fun onFailure(call: Call<TextResp?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(applicationContext, "Комментарий не добавлен!", Toast.LENGTH_SHORT).show()

            }
        })

    }

        companion object {
        @JvmStatic
        fun newInstance(context: Context, id: Long): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(context.getString(R.string.EXTRAS_ID), id)
            return intent
        }
    }

    private fun showMainActivity() {
        startActivity(MainActivity.newInstance(this))
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

    private fun changeColor(button: Button, color: Int) {
        button.setBackgroundColor(color)
    }
}
