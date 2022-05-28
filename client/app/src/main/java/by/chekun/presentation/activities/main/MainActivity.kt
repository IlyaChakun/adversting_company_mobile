package by.chekun.presentation.activities.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.chekun.R
import by.chekun.databinding.ActivityMainBinding
import by.chekun.di.component.ViewModelComponent
import by.chekun.domain.AllAdvertisementsViewModel
import by.chekun.presentation.activities.detail.DetailActivity
import by.chekun.presentation.adapter.AdvertisementAdapter
import by.chekun.presentation.base.BaseActivity
import by.chekun.presentation.item.CarItemClickListener
import by.chekun.repository.database.entity.advertisement.view.AdvertisementResp
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    var viewModel: AllAdvertisementsViewModel? = null
        @Inject set

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.title = "Ads list"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel?.getAllItems()
        viewModel?.getLiveDataItems()?.observe(this, Observer { it?.let { initRecyclerView(it) } })
    }

    private fun initRecyclerView(cars: List<AdvertisementResp>) {

        val manager = LinearLayoutManager(this)
        val advertisementAdapter = AdvertisementAdapter( this, cars, itemClickListener)
        advertisementAdapter.setItemClickListener(itemClickListener)
        rvUsers.layoutManager = manager
        rvUsers.adapter = advertisementAdapter
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
        this.startActivity(DetailActivity.newInstance(this, id))
    }
}
