package by.chekun.presentation.activities.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.chekun.R
import by.chekun.databinding.ActivityMainBinding
import by.chekun.di.component.ViewModelComponent
import by.chekun.domain.AllCarsViewModel
import by.chekun.presentation.activities.detail.DetailActivity
import by.chekun.presentation.adapter.CarAdapter
import by.chekun.presentation.base.BaseActivity
import by.chekun.presentation.item.CarItemClickListener
import by.chekun.repository.database.entity.car.view.CarDto
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    var viewModel: AllCarsViewModel? = null
        @Inject set

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.title = "Каталог авто"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel?.getAllItems()
        viewModel?.getLiveDataItems()?.observe(this, Observer { it?.let { initRecyclerView(it) } })
    }

    private fun initRecyclerView(cars: List<CarDto>) {

        val manager = LinearLayoutManager(this)
        val carAdapter = CarAdapter( this, cars, itemClickListener)
        carAdapter.setItemClickListener(itemClickListener)
        rvUsers.layoutManager = manager
        rvUsers.adapter = carAdapter
    }


    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    private val itemClickListener = object : CarItemClickListener<CarDto> {
        override fun openDetail(entity: CarDto) {
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
