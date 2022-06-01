package by.chekun.presentation.activities.add

//import by.chekun.multispinner.MultiSpinnerSearch
//import by.chekun.multispinner.SingleSpinner
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.os.FileUtils
import android.text.InputFilter
import android.util.Base64.*
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import by.chekun.R
import by.chekun.di.component.ViewModelComponent
import by.chekun.domain.AddAdvertisementViewModel
import by.chekun.presentation.activities.main.MainActivity
import by.chekun.presentation.base.BaseActivity
import by.chekun.repository.database.entity.User
import by.chekun.repository.database.entity.advertisement.AddAdvertisementRequest
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import by.chekun.repository.database.entity.advertisement.view.AdvertisementStatus
import by.chekun.repository.database.entity.advertisement.view.AdvertisementType
import by.chekun.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.inject.Inject


class AddAdvertisementActivity : BaseActivity() {

    var advViewModel: AddAdvertisementViewModel? = null
        @Inject set

    private var saveButton: Button? = null
    private val addActivitySpinners: MutableMap<String, Spinner> = HashMap()
    private val editTextFieldsMap: MutableMap<String, TextViewValidStatus> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.title = "Опубликовать объявление"

        setContentView(R.layout.activity_add_car)

        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        createSaveButtonListener()
        createSpinnersMap()
        createEditTextFieldsMap()

        //SpinnerHolder(viewModel, addActivitySpinners).initSpinners(this)
        initImageSaving()
        //initDigitsFilter()
        initTextWatchers()
    }


    private fun createSaveButtonListener() {
        saveButton = findViewById(R.id.car_save_button)
    }

    private fun createEditTextFieldsMap() {
        val title: TextView = findViewById(R.id.txt_title)
        val body: TextView = findViewById(R.id.txt_body)


        editTextFieldsMap[TITLE_TEXT_VIEW_KEY] = TextViewValidStatus(title, false)
        editTextFieldsMap[BODY_TEXT_VIEW_KEY] = TextViewValidStatus(body, false)
    }


    private fun isAllFieldsValid(): Boolean {
        var isValid = true

//        this.addActivitySpinners.forEach { (key, spinner) ->
//            if (spinner.selectedItem == null) {
//                isValid = false
//            }
//        }
//
//        this.editTextFieldsMap.forEach { (key, textViewValidStatus) ->
//            if (!textViewValidStatus.isValid) {
//                isValid = false
//            }
//        }

        if (!isValid) {
            showToast("Заполните все обязательные поля!")
        }

        return isValid
    }

    private fun initTextWatchers() {

//        val priceTextField: TextView = editTextFieldsMap[PRICE_TEXT_VIEW_KEY]!!.textField
//        priceTextField.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                val textToDouble: Double = try {
//                    s.toString().toDouble()
//                } catch (e: NumberFormatException) {
//                    0.0
//                }
//                if (textToDouble > 0) {
//                    priceTextField.error = null
//                    editTextFieldsMap[PRICE_TEXT_VIEW_KEY]?.isValid = true
//                } else {
//                    priceTextField.error = "Стоимость должна быть больше 0!"
//                    editTextFieldsMap[PRICE_TEXT_VIEW_KEY]?.isValid = false
//                }
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//        })

//        val engineCapacityTextField: TextView = editTextFieldsMap[ENGINE_CAPACITY_TEXT_VIEW_KEY]!!.textField
//        engineCapacityTextField.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                val textToDouble: Double = try {
//                    s.toString().toDouble()
//                } catch (e: NumberFormatException) {
//                    0.0
//                }
//                if (textToDouble > 0 && textToDouble <= 30) {
//                    engineCapacityTextField.error = null
//                    editTextFieldsMap[ENGINE_CAPACITY_TEXT_VIEW_KEY]?.isValid = true
//                } else {
//                    engineCapacityTextField.error = "Объем двигателя должен быть больше 0 но меньшк 30 литров!"
//                    editTextFieldsMap[ENGINE_CAPACITY_TEXT_VIEW_KEY]?.isValid = false
//                }
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//        })

    }

    private fun initImageSaving() {
        val imageSaveButton: Button = findViewById(R.id.img_pick_btn)
        imageSaveButton.setOnClickListener {
            if (VERSION.SDK_INT >= VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    requestPermissions(permissions, PERMISSION_CODE);
                } else {
                    pickImageFromGallery();
                }
            } else {
                pickImageFromGallery();
            }
        }
    }


    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private const val IMAGE_PICK_CODE = 1000;

        //Permission code
        private const val PERMISSION_CODE = 1001;
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    var uri: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val imageView: ImageView = findViewById(R.id.image_view)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            uri = data?.data

            imageView.setImageURI(data?.data)
        }
    }


    private fun initDigitsFilter() {
        val priceTextView = editTextFieldsMap[PRICE_TEXT_VIEW_KEY]!!.textField
        priceTextView.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(10, 2))
    }


    private fun createSpinnersMap() {
        val typeSpinner: Spinner = findViewById(R.id.type_spinner)

        ArrayAdapter.createFromResource(
                this,
                R.array.types_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            typeSpinner.adapter = adapter
        }

        addActivitySpinners[TYPE_SPINNER_KEY] = typeSpinner
    }


    @RequiresApi(VERSION_CODES.O)
    fun clickSaveCar(view: View?) {

        if (this.isAllFieldsValid()) {

            val type = addActivitySpinners[TYPE_SPINNER_KEY]!!.selectedItem.toString()

            val title = findViewById<EditText>(R.id.txt_title).text.toString()

            val body = findViewById<EditText>(R.id.txt_body).text.toString()

            val carRequestDto = AddAdvertisementRequest()

            carRequestDto.title = title
            carRequestDto.body = body
            carRequestDto.type = AdvertisementType.fromOrbisString(type)
            carRequestDto.status = AdvertisementStatus.PENDING

            val user: User? = viewModel?.getCurrentUser()
            carRequestDto.userId = user?.id

            // getMe into mobile db


            var byteArray: ByteArray? = null
            val carImage = findViewById<ImageView>(R.id.image_view)
            val mBitmap = (carImage.drawable as BitmapDrawable).bitmap
            val bos = ByteArrayOutputStream()
            mBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
            byteArray = bos.toByteArray()
            val filesDir: File = applicationContext.filesDir
            val file = File(filesDir, "image" + ".png")
            val fos = FileOutputStream(file)
            fos.write(byteArray)
            fos.flush()
            fos.close()

            val encoder: Base64.Encoder = Base64.getEncoder()
            val encodedString: String = encoder.encodeToString(file.toString().toByteArray())
            carRequestDto.picture = encodedString
//            val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
//            val multipartBody: MultipartBody.Part = MultipartBody.Part.createFormData("picture", file.name, requestBody)


            advViewModel?.saveAdvertisement(carRequestDto)?.enqueue(object : Callback<AdvertisementResp> {

                override fun onResponse(call: Call<AdvertisementResp>?, response: Response<AdvertisementResp>) {

                    Toast.makeText(applicationContext, response.code().toString() + " ", Toast.LENGTH_SHORT).show()

                    if (response.isSuccessful) {

                        val carId = response.body()?.id

                        showToast("Advertisement added, new id = " + carId)

                          showMainActivity()
//                        val req: Call<AdvertisementResp>? = viewModel?.postImage(carId!!, multipartBody)
//                        req?.enqueue(object : Callback<AdvertisementResp?> {
//                            override fun onResponse(call: Call<AdvertisementResp?>, response: Response<AdvertisementResp?>) {
//                                Toast.makeText(applicationContext, response.code().toString() + " ", Toast.LENGTH_SHORT).show()
//
//                                showMainActivity()
//                            }
//
//                            override fun onFailure(call: Call<AdvertisementResp?>, t: Throwable) {
//                                Toast.makeText(applicationContext, "Request failed", Toast.LENGTH_SHORT).show()
//
//                            }
//                        })

                    } else {
                        showToast("Can`t create car! Fill fields according with pattern.")
                    }

                }

                override fun onFailure(call: Call<AdvertisementResp>?, t: Throwable?) {
                    Toast.makeText(applicationContext, "Request failed", Toast.LENGTH_SHORT).show()
                    t?.printStackTrace()
                }
            })
        }
    }


    @RequiresApi(VERSION_CODES.O)
    fun clickDraftCar(view: View?) {

        if (this.isAllFieldsValid()) {

            val type = addActivitySpinners[TYPE_SPINNER_KEY]!!.selectedItem.toString()

            val title = findViewById<EditText>(R.id.txt_title).text.toString()

            val body = findViewById<EditText>(R.id.txt_body).text.toString()

            val carRequestDto = AddAdvertisementRequest()

            carRequestDto.title = title
            carRequestDto.body = body
            carRequestDto.type = AdvertisementType.fromOrbisString(type)
            carRequestDto.status = AdvertisementStatus.DRAFT

            val user: User? = viewModel?.getCurrentUser()
            carRequestDto.userId = user?.id

            // getMe into mobile db


            var byteArray: ByteArray? = null
            val carImage = findViewById<ImageView>(R.id.image_view)
            val mBitmap = (carImage.drawable as BitmapDrawable).bitmap
            val bos = ByteArrayOutputStream()
            mBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
            byteArray = bos.toByteArray()
            val filesDir: File = applicationContext.filesDir
            val file = File(filesDir, "image" + ".png")
            val fos = FileOutputStream(file)
            fos.write(byteArray)
            fos.flush()
            fos.close()

            val encoder: Base64.Encoder = Base64.getEncoder()
            val encodedString: String = encoder.encodeToString(file.toString().toByteArray())
            carRequestDto.picture = encodedString
//            val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
//            val multipartBody: MultipartBody.Part = MultipartBody.Part.createFormData("picture", file.name, requestBody)


            advViewModel?.saveAdvertisement(carRequestDto)?.enqueue(object : Callback<AdvertisementResp> {

                override fun onResponse(call: Call<AdvertisementResp>?, response: Response<AdvertisementResp>) {

                    Toast.makeText(applicationContext, response.code().toString() + " ", Toast.LENGTH_SHORT).show()

                    if (response.isSuccessful) {

                        val carId = response.body()?.id

                        showToast("Advertisement added, new id = " + carId)

                          showMainActivity()
//                        val req: Call<AdvertisementResp>? = viewModel?.postImage(carId!!, multipartBody)
//                        req?.enqueue(object : Callback<AdvertisementResp?> {
//                            override fun onResponse(call: Call<AdvertisementResp?>, response: Response<AdvertisementResp?>) {
//                                Toast.makeText(applicationContext, response.code().toString() + " ", Toast.LENGTH_SHORT).show()
//
//                                showMainActivity()
//                            }
//
//                            override fun onFailure(call: Call<AdvertisementResp?>, t: Throwable) {
//                                Toast.makeText(applicationContext, "Request failed", Toast.LENGTH_SHORT).show()
//
//                            }
//                        })

                    } else {
                        showToast("Can`t create car! Fill fields according with pattern.")
                    }

                }

                override fun onFailure(call: Call<AdvertisementResp>?, t: Throwable?) {
                    Toast.makeText(applicationContext, "Request failed", Toast.LENGTH_SHORT).show()
                    t?.printStackTrace()
                }
            })
        }
    }


    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun showMainActivity() {
        startActivity(MainActivity.newInstance(this))
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_new_car, menu)
        return true
    }

}


/*
 var byteArray: ByteArray? = null

        val carImage = findViewById<ImageView>(R.id.image_view)
        val mBitmap = (carImage.drawable as BitmapDrawable).bitmap

        val bos = ByteArrayOutputStream()
        mBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
        byteArray = bos.toByteArray()
        val filesDir: File = applicationContext.filesDir
        val file = File(filesDir, "image" + ".png")
        val fos = FileOutputStream(file)
        fos.write(byteArray)
        fos.flush()
        fos.close()

        val builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)


        builder.addFormDataPart("event_name", "xyz")
        builder.addFormDataPart("desc", "Lorem ipsum")

        // Single Image

        // Single Image
        builder.addFormDataPart("files", file.name, RequestBody.create(MediaType.parse("image/*"), file))

        // Multiple Images


        val requestBody = builder.build()

//        var byteArray: ByteArray? = null
//
//        val carImage = findViewById<ImageView>(R.id.image_view)
//
//        val mBitmap = (carImage.drawable as BitmapDrawable).bitmap
//
//
//        val bos = ByteArrayOutputStream()
//        mBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
//        byteArray = bos.toByteArray()
//        val filesDir: File = applicationContext.filesDir
//        val file = File(filesDir, "image" + ".png")
//        val fos = FileOutputStream(file)
//        fos.write(byteArray)
//        fos.flush()
//        fos.close()
//
//        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
//        val body: MultipartBody.Part = MultipartBody.Part.createFormData("picture", file.name, requestBody)
//        val name = RequestBody.create(MediaType.parse("text/plain"), "picture")



       carRequestDto.picture = requestBody

//        val req: Call<ResponseBody?>? = viewModel?.postImage(body, carRequestDto)
//        req?.enqueue(object : Callback<ResponseBody?> {
//            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
//                Toast.makeText(applicationContext, response.code().toString() + " ", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
//                Toast.makeText(applicationContext, "Request failed", Toast.LENGTH_SHORT).show()
//                t.printStackTrace()
//            }
//        })
 */


 */