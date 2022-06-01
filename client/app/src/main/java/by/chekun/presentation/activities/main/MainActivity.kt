package by.chekun.presentation.activities.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.chekun.R
import by.chekun.databinding.ActivityMainBinding
import by.chekun.di.component.ViewModelComponent
import by.chekun.domain.AllAdvertisementsViewModel
import by.chekun.domain.UserViewModel
import by.chekun.presentation.activities.detail.DetailActivity
import by.chekun.presentation.activities.user.ProfileActivity
import by.chekun.presentation.adapter.AdvertisementAdapter
import by.chekun.presentation.base.BaseActivity
import by.chekun.presentation.item.CarItemClickListener
import by.chekun.repository.database.entity.User
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    var advViewModel: AllAdvertisementsViewModel? = null
        @Inject set

    var userViewModel: UserViewModel? = null
        @Inject set

    lateinit var binding: ActivityMainBinding
    var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.title = "Ads list"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        currentUser = userViewModel?.getCurrentUser()

        if(currentUser != null)
            advViewModel?.getAllItems()
        else {
            advViewModel?.getPublishItems()
        }
        advViewModel?.getLiveDataItems()?.observe(this, Observer { it?.let { initRecyclerView(it) } })
    }

    private fun initRecyclerView(cars: List<AdvertisementResp>) {

        val manager = LinearLayoutManager(this)
        val advertisementAdapter = AdvertisementAdapter( this, cars, itemClickListener)
        advertisementAdapter.setItemClickListener(itemClickListener)
        rvUsers.layoutManager = manager
        rvUsers.adapter = advertisementAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(currentUser != null)
            menuInflater.inflate(R.menu.auth_user_menu, menu)
        else {
            menuInflater.inflate(R.menu.non_auth_user_menu, menu)
        }

        menuInflater.inflate(R.menu.menu_add_new_car, menu)
        return true
    }


    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    private val itemClickListener = object : CarItemClickListener<AdvertisementResp> {
        override fun openDetail(entity: AdvertisementResp) {
            openItemDetail(entity.id)
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun openItemDetail(id: Long) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("advId", id)
        startActivity(intent)
    }

}
