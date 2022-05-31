package by.chekun.presentation.activities.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.chekun.R
import by.chekun.databinding.ActivityAdminMainBinding
import by.chekun.di.component.ViewModelComponent
import by.chekun.domain.PendingAdvertisementsViewModel
import by.chekun.presentation.activities.detail.DetailActivity
import by.chekun.presentation.activities.login.LoginActivity
import by.chekun.presentation.adapter.PendingAdvertisementAdapter
import by.chekun.presentation.base.BaseActivity
import by.chekun.presentation.item.CarItemClickListener
import by.chekun.repository.database.entity.advertisement.RespChangeStatus
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import by.chekun.repository.database.entity.advertisement.view.AdvertisementStatus
import by.chekun.repository.database.entity.user.RegisterRequest
import by.chekun.repository.database.entity.user.TextResp
import kotlinx.android.synthetic.main.activity_admin_main.*
import kotlinx.android.synthetic.main.item_admin_advertisement.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainAdminActivity : BaseActivity() {

    var viewModel: PendingAdvertisementsViewModel? = null
        @Inject set

    lateinit var binding: ActivityAdminMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.title = "Pending ads"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_main)
        viewModel?.getAllItems()
        viewModel?.getLiveDataItems()?.observe(this, Observer { it?.let { initRecyclerView(it) } })

    }

    private fun initRecyclerView(cars: List<AdvertisementResp>) {

        val manager = LinearLayoutManager(this)
        val advertisementAdapter = PendingAdvertisementAdapter( this, cars, itemClickListener)
        advertisementAdapter.setItemClickListener(itemClickListener)
        rvUsers.layoutManager = manager
        rvUsers.adapter = advertisementAdapter
    }


    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainAdminActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }




    private val itemClickListener = object : CarItemClickListener<AdvertisementResp> {
        override fun openDetail(entity: AdvertisementResp) {
            setPublishAdvStatus(entity.id)
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun openItemDetail(id: Long) {
        this.startActivity(DetailActivity.newInstance(this, id))
    }

    private fun setPublishAdvStatus(id: Long) {

        val req = RespChangeStatus()

        req.advertisementId = id
        req.status = AdvertisementStatus.PUBLISHED

        viewModel?.setPublishStatus(req)?.enqueue(object : Callback<TextResp> {

            override fun onResponse(call: Call<TextResp>?, response: Response<TextResp>) {

                Toast.makeText(applicationContext, response.code().toString() + " ", Toast.LENGTH_SHORT).show()

                if (response.isSuccessful) {

                    Toast.makeText(applicationContext, "Статус изменен!", Toast.LENGTH_SHORT).show()
                    showMainAdminActivity()

                } else {
                    Toast.makeText(applicationContext, "Не удалось изменить статус!", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<TextResp>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Wrong login or password", Toast.LENGTH_SHORT).show()
                t?.printStackTrace()
            }
        })
    }

    private fun showMainAdminActivity() {
        finish();
        startActivity(MainAdminActivity.newInstance(this))
    }
}
