package by.chekun.presentation.activities.login


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import by.chekun.R
import by.chekun.di.component.ViewModelComponent
import by.chekun.domain.UserViewModel
import by.chekun.presentation.activities.add.DecimalDigitsInputFilter
import by.chekun.presentation.activities.add.TextViewValidStatus
import by.chekun.presentation.activities.main.MainActivity
import by.chekun.presentation.activities.main.MainAdminActivity
import by.chekun.presentation.base.BaseActivity
import by.chekun.repository.database.entity.User
import by.chekun.repository.database.entity.user.AccessTokenDTO
import by.chekun.repository.database.entity.user.LoginRequest
import by.chekun.repository.database.entity.user.UserResp
import by.chekun.utils.LOGIN_TEXT_VIEW_KEY
import by.chekun.utils.PASSWORD_TEXT_VIEW_KEY
import by.chekun.utils.PRICE_TEXT_VIEW_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject


class LoginActivity : BaseActivity() {


    private var saveButton: Button? = null
    private val editTextFieldsMap: MutableMap<String, TextViewValidStatus> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.title = "Авторизация"

        setContentView(R.layout.activity_login)

        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        createSaveButtonListener()
        createEditTextFieldsMap()

        initTextWatchers()
    }


    private fun createSaveButtonListener() {
        saveButton = findViewById(R.id.car_save_button)
    }

    private fun createEditTextFieldsMap() {
        val login: TextView = findViewById(R.id.txt_login)
        val pass: TextView = findViewById(R.id.txt_pass)


        editTextFieldsMap[LOGIN_TEXT_VIEW_KEY] = TextViewValidStatus(login, false)
        editTextFieldsMap[PASSWORD_TEXT_VIEW_KEY] = TextViewValidStatus(pass, false)
    }


    private fun isAllFieldsValid(): Boolean {
        val isValid = true

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

    private fun initDigitsFilter() {
        val priceTextView = editTextFieldsMap[PRICE_TEXT_VIEW_KEY]!!.textField
        priceTextView.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(10, 2))
    }


    fun clockLogin(view: View?) {

        if (this.isAllFieldsValid()) {

            val login = findViewById<EditText>(R.id.txt_login).text.toString()

            val pass = findViewById<EditText>(R.id.txt_pass).text.toString()

            val req = LoginRequest()

            req.login = login
            req.password = pass

            viewModel?.login(req)?.enqueue(object : Callback<AccessTokenDTO> {

                override fun onResponse(
                    call: Call<AccessTokenDTO>,
                    response: Response<AccessTokenDTO>
                ) {

                    Toast.makeText(applicationContext, response.code().toString() + " ", Toast.LENGTH_SHORT).show()

                    if (response.isSuccessful) {

                        val tokenDTO = response.body()

                        val headers = HashMap<String, String>()
                        headers["Authorization"] = "Bearer " + response.body()?.accessToken

                        val getMeCallBack: Call<UserResp>? = viewModel?.getMe(headers)

                        getMeCallBack?.enqueue(object : Callback<UserResp?> {

                            override fun onResponse(call: Call<UserResp?>, response: Response<UserResp?>) {

                                viewModel?.deleteAll()
                                val userResp = response.body()
                                val user = User(
                                    id = userResp?.id!!,
                                    firstName = userResp.firstName!!,
                                    lastName = userResp.lastName!!,
                                    email = userResp.email!!,
                                    accessToken = tokenDTO?.accessToken,
                                    tokenType = tokenDTO?.tokenType,
                                    expiresIn = tokenDTO!!.expiresIn,
                                    role = userResp.roles?.get(0)!!.role
                                )

                                showToast("Login successful")
                                viewModel?.saveUser(user)

                                if(userResp.roles[0].role.equals("ADMIN")) {
                                    showMainAdminActivity()
                                } else {
                                    showMainActivity()
                                }
                            }

                            override fun onFailure(call: Call<UserResp?>, t: Throwable) {
                                t.printStackTrace()
                                Toast.makeText(applicationContext, "Request failed", Toast.LENGTH_SHORT).show()

                            }
                        })


                        showMainActivity()

                    } else {
                        showToast("Wrong login or password.")
                    }

                }

                override fun onFailure(call: Call<AccessTokenDTO>, t: Throwable) {
                    Toast.makeText(applicationContext, "Wrong login or password", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            })
        }
    }


    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun showMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showMainAdminActivity() {
        startActivity(MainAdminActivity.newInstance(this))
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login_menu, menu)
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

}

