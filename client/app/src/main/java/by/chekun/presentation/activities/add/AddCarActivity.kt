package by.chekun.presentation.activities.add

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import android.widget.*
import by.chekun.R
import by.chekun.di.component.ViewModelComponent
import by.chekun.domain.AddCarViewModel
//import by.chekun.multispinner.MultiSpinnerSearch
//import by.chekun.multispinner.SingleSpinner
import by.chekun.presentation.activities.main.MainActivity
import by.chekun.presentation.base.BaseActivity
import by.chekun.repository.database.entity.brand.ReleaseYearDto
import by.chekun.repository.database.entity.car.AddRequestDto
import by.chekun.repository.database.entity.car.MileageDto
import by.chekun.repository.database.entity.car.view.AdvertisementResp
import by.chekun.utils.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap


class AddCarActivity : BaseActivity() {

    var viewModel: AddCarViewModel? = null
        @Inject set

    private var saveButton: Button? = null
    private val addActivitySpinners: MutableMap<String, Spinner> = HashMap()
    private val editTextFieldsMap: MutableMap<String, TextViewValidStatus> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.title = "Добавление авто"

        setContentView(R.layout.activity_add_car)

        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        createSaveButtonListener()
        createSpinnersMap()
        createEditTextFieldsMap()

        SpinnerHolder(viewModel, addActivitySpinners).initSpinners(this)
        initImageSaving()
        initDigitsFilter()
        initTextWatchers()
    }


    private fun createSaveButtonListener() {
        saveButton = findViewById(R.id.car_save_button)
    }

    private fun createEditTextFieldsMap() {
        val priceTextField: TextView = findViewById(R.id.txt_price_value)
        val engineCapacityTextField: TextView = findViewById(R.id.txt_engine_capacity)
        val mileageTextField: TextView = findViewById(R.id.txt_mileage)


        editTextFieldsMap[PRICE_TEXT_VIEW_KEY] = TextViewValidStatus(priceTextField, false)
        editTextFieldsMap[ENGINE_CAPACITY_TEXT_VIEW_KEY] = TextViewValidStatus(engineCapacityTextField, false)
        editTextFieldsMap[MILEAGE_TEXT_VIEW_KEY] = TextViewValidStatus(mileageTextField, false)
    }


    private fun isAllFieldsValid(): Boolean {
        var isValid = true

        this.addActivitySpinners.forEach { (key, spinner) ->
            if (spinner.selectedItem == null) {
                isValid = false
            }
        }

        this.editTextFieldsMap.forEach { (key, textViewValidStatus) ->
            if (!textViewValidStatus.isValid) {
                isValid = false
            }
        }

        if (!isValid) {
            showToast("Заполните все обязательные поля!")
        }

        return isValid
    }

    private fun initTextWatchers() {

        val priceTextField: TextView = editTextFieldsMap[PRICE_TEXT_VIEW_KEY]!!.textField
        priceTextField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val textToDouble: Double = try {
                    s.toString().toDouble()
                } catch (e: NumberFormatException) {
                    0.0
                }
                if (textToDouble > 0) {
                    priceTextField.error = null
                    editTextFieldsMap[PRICE_TEXT_VIEW_KEY]?.isValid = true
                } else {
                    priceTextField.error = "Стоимость должна быть больше 0!"
                    editTextFieldsMap[PRICE_TEXT_VIEW_KEY]?.isValid = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        val engineCapacityTextField: TextView = editTextFieldsMap[ENGINE_CAPACITY_TEXT_VIEW_KEY]!!.textField
        engineCapacityTextField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val textToDouble: Double = try {
                    s.toString().toDouble()
                } catch (e: NumberFormatException) {
                    0.0
                }
                if (textToDouble > 0 && textToDouble <= 30) {
                    engineCapacityTextField.error = null
                    editTextFieldsMap[ENGINE_CAPACITY_TEXT_VIEW_KEY]?.isValid = true
                } else {
                    engineCapacityTextField.error = "Объем двигателя должен быть больше 0 но меньшк 30 литров!"
                    editTextFieldsMap[ENGINE_CAPACITY_TEXT_VIEW_KEY]?.isValid = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        val mileageTextField: TextView = editTextFieldsMap[MILEAGE_TEXT_VIEW_KEY]!!.textField
        mileageTextField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val textToDouble: Double = try {
                    s.toString().toDouble()
                } catch (e: NumberFormatException) {
                    0.0
                }
                if (textToDouble in 0.0..2000000.0) {
                    mileageTextField.error = null
                    editTextFieldsMap[MILEAGE_TEXT_VIEW_KEY]?.isValid = true
                } else {
                    mileageTextField.error = "Пробег должен быть больше либо равен 0 и меньше чем 2_000_000!"
                    editTextFieldsMap[MILEAGE_TEXT_VIEW_KEY]?.isValid = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

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
        val brandSpinner: Spinner = findViewById(R.id.brand_spinner)
        val modelSpinner: Spinner = findViewById(R.id.model_spinner)
        val releaseYearSpinner: Spinner = findViewById(R.id.release_year_spinner)
        val generationSpinner: Spinner = findViewById(R.id.generation_spinner)
        val bodyTypeSpinner: Spinner = findViewById(R.id.body_type_spinner)


//        val conditionSpinner: SingleSpinner = findViewById(R.id.condition_spinner)
//
//        val engineSpinner: SingleSpinner = findViewById(R.id.engine_spinner)
//
//        val transmissionSpinner: SingleSpinner = findViewById(R.id.transmission_type_spinner)
//        val wheelDriveTypeSpinner: SingleSpinner = findViewById(R.id.wheel_drive_spinner)
//        val colorSpinner: SingleSpinner = findViewById(R.id.color_spinner)
//        val interiorColorSpinner: SingleSpinner = findViewById(R.id.interior_color_spinner)
//        val interiorMaterialSpinner: SingleSpinner = findViewById(R.id.interior_material_spinner)
//
//        val safetiesMultipleSpinner: MultiSpinnerSearch = findViewById(R.id.safeties_spinner)
//        val interiorMultipleSpinner: MultiSpinnerSearch = findViewById(R.id.interior_spinner)

        addActivitySpinners[BRAND_SPINNER_KEY] = brandSpinner
        addActivitySpinners[MODEL_SPINNER_KEY] = modelSpinner
        addActivitySpinners[RELEASE_YEAR_SPINNER_KEY] = releaseYearSpinner
        addActivitySpinners[BODY_TYPE_SPINNER_KEY] = bodyTypeSpinner
        addActivitySpinners[GENERATION_SPINNER_KEY] = generationSpinner

//        addActivitySpinners[CONDITION_SPINNER_KEY] = conditionSpinner
//
//        addActivitySpinners[ENGINE_TYPE_SPINNER_KEY] = engineSpinner
//
//        addActivitySpinners[TRANSMISSION_TYPE_SPINNER_KEY] = transmissionSpinner
//        addActivitySpinners[WHEEL_DRIVE_TYPE_SPINNER_KEY] = wheelDriveTypeSpinner
//        addActivitySpinners[COLOR_TYPE_SPINNER_KEY] = colorSpinner
//        addActivitySpinners[INTERIOR_COLOR_TYPE_SPINNER_KEY] = interiorColorSpinner
//        addActivitySpinners[INTERIOR_MATERIAL_TYPE_SPINNER_KEY] = interiorMaterialSpinner
//
//
//        addActivitySpinners[INTERIOR_MULTIPLE_SPINNER_KEY] = interiorMultipleSpinner
//        addActivitySpinners[SAFETIES_MULTIPLE_SPINNER_KEY] = safetiesMultipleSpinner


    }


    fun clickSaveCar(view: View?) {

        if (this.isAllFieldsValid()) {

            val brandId: Long = addActivitySpinners[BRAND_SPINNER_KEY]!!.selectedItemId
            val modelId: Long = addActivitySpinners[MODEL_SPINNER_KEY]!!.selectedItemId
            val generationId: Long = addActivitySpinners[GENERATION_SPINNER_KEY]!!.selectedItemId
            val bodyTypeId: Long = addActivitySpinners[BODY_TYPE_SPINNER_KEY]!!.selectedItemId
            val transmissionTypeId: Long = addActivitySpinners[TRANSMISSION_TYPE_SPINNER_KEY]!!.selectedItemId
            val engineTypeId: Long = addActivitySpinners[ENGINE_TYPE_SPINNER_KEY]!!.selectedItemId
            val wheelDriveTypeId: Long = addActivitySpinners[WHEEL_DRIVE_TYPE_SPINNER_KEY]!!.selectedItemId
            val colorId: Long = addActivitySpinners[COLOR_TYPE_SPINNER_KEY]!!.selectedItemId
            val conditionId: Long = addActivitySpinners[CONDITION_SPINNER_KEY]!!.selectedItemId
            val interiorColorId: Long = addActivitySpinners[INTERIOR_COLOR_TYPE_SPINNER_KEY]!!.selectedItemId
            val interiorMaterialId: Long = addActivitySpinners[INTERIOR_MATERIAL_TYPE_SPINNER_KEY]!!.selectedItemId
            val releaseYear: Int = (addActivitySpinners[RELEASE_YEAR_SPINNER_KEY]!!.selectedItem as ReleaseYearDto).releaseYear

//            val safetyIds = (addActivitySpinners[SAFETIES_MULTIPLE_SPINNER_KEY] as MultiSpinnerSearch).selectedIds
//            val interiorIds = (addActivitySpinners[INTERIOR_MULTIPLE_SPINNER_KEY] as MultiSpinnerSearch).selectedIds

            val priceEditText = findViewById<EditText>(R.id.txt_price_value)
            val price: Double = priceEditText.text.toString().toDouble()

            val mileageEditText = findViewById<EditText>(R.id.txt_mileage)
            val mileageDistance: Int = mileageEditText.text.toString().toInt()
            val mileage = MileageDto()
            mileage.mileage = mileageDistance
            val carRequestDto = AddRequestDto()
//            carRequestDto.brandId = brandId
//            carRequestDto.modelId = modelId
//            carRequestDto.generationId = generationId
//            carRequestDto.bodyTypeId = bodyTypeId
//            carRequestDto.transmissionTypeId = transmissionTypeId
//            carRequestDto.engineTypeId = engineTypeId
//            val engineCapacityEditText = findViewById<EditText>(R.id.txt_engine_capacity)
//            carRequestDto.engineCapacity = engineCapacityEditText.text.toString().toDouble()
//            carRequestDto.wheelDriveTypeId = wheelDriveTypeId
//            carRequestDto.colorId = colorId
//            carRequestDto.conditionId = conditionId
//            carRequestDto.interiorColorId = interiorColorId
//            carRequestDto.interiorMaterialId = interiorMaterialId
//            carRequestDto.releaseYear = releaseYear
//            carRequestDto.price = price
//            carRequestDto.mileage = mileage
//            carRequestDto.safetyIds = safetyIds
//            carRequestDto.interiorIds = interiorIds
            val descriptionEditText = findViewById<EditText>(R.id.txt_description_value)
            carRequestDto.description = descriptionEditText.text.toString()


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
            val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
            val multipartBody: MultipartBody.Part = MultipartBody.Part.createFormData("picture", file.name, requestBody)


            viewModel?.saveCar(carRequestDto)?.enqueue(object : Callback<AdvertisementResp> {

                override fun onResponse(call: Call<AdvertisementResp>?, response: Response<AdvertisementResp>) {

                    Toast.makeText(applicationContext, response.code().toString() + " ", Toast.LENGTH_SHORT).show()

                    if (response.isSuccessful) {

                        val carId = response.body()?.id

                        showToast("CarDto added, new id = " + carId)

                      //  showMainActivity()
                        val req: Call<AdvertisementResp>? = viewModel?.postImage(carId!!, multipartBody)
                        req?.enqueue(object : Callback<AdvertisementResp?> {
                            override fun onResponse(call: Call<AdvertisementResp?>, response: Response<AdvertisementResp?>) {
                                Toast.makeText(applicationContext, response.code().toString() + " ", Toast.LENGTH_SHORT).show()

                                showMainActivity()
                            }

                            override fun onFailure(call: Call<AdvertisementResp?>, t: Throwable) {
                                Toast.makeText(applicationContext, "Request failed", Toast.LENGTH_SHORT).show()

                            }
                        })

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