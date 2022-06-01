package by.chekun.presentation.activities.user


import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import by.chekun.R
import by.chekun.databinding.CarDetailBinding
import by.chekun.databinding.UserBinding
import by.chekun.di.component.ViewModelComponent
import by.chekun.domain.UserViewModel
import by.chekun.presentation.activities.add.DecimalDigitsInputFilter
import by.chekun.presentation.activities.add.TextViewValidStatus
import by.chekun.presentation.activities.login.LoginActivity
import by.chekun.presentation.activities.main.MainActivity
import by.chekun.presentation.activities.main.MainAdminActivity
import by.chekun.presentation.base.BaseActivity
import by.chekun.repository.database.entity.User
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import by.chekun.repository.database.entity.user.AccessTokenDTO
import by.chekun.repository.database.entity.user.LoginRequest
import by.chekun.repository.database.entity.user.UserResp
import by.chekun.utils.LOGIN_TEXT_VIEW_KEY
import by.chekun.utils.PASSWORD_TEXT_VIEW_KEY
import by.chekun.utils.PRICE_TEXT_VIEW_KEY
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject


class ProfileActivity : BaseActivity() {

    private lateinit var binding: UserBinding


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.title = "Профиль"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        initTextWatchers()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initTextWatchers() {

        val user: User? = viewModel?.getCurrentUser()

        if(user != null)
            initDataBinding(user)
        else {
            showLoginActivity()
        }
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

    private fun showLoginActivity() {
        startActivity(LoginActivity.newInstance(this))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initDataBinding(user: User) {
        binding.user = user
    }


    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }


    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_new_car, menu)
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

}

