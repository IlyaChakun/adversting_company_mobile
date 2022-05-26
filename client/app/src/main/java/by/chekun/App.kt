package by.chekun

import android.app.Application
import androidx.room.Room
import by.chekun.di.component.*
import by.chekun.di.module.ApiModule
import by.chekun.di.module.DatabaseModule
import by.chekun.di.module.RepositoryModule
import by.chekun.di.module.ViewModelModule
import by.chekun.repository.database.AppDatabase


class App : Application() {

    private var viewModelComponent: ViewModelComponent? = null
    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        initRoom()
        initDagger()
    }

    private fun initRoom() {
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    private fun initDagger() {
        val apiComponent = DaggerApiComponent.builder()
                .apiModule(ApiModule())
                .build()

        val databaseComponent = DaggerDatabaseComponent.builder()
                .databaseModule(DatabaseModule(this!!.database!!))
                .build()

        val repositoryComponent = DaggerRepositoryComponent.builder()
                .apiComponent(apiComponent)
                .databaseComponent(databaseComponent)
                .repositoryModule(RepositoryModule())
                .build()

        viewModelComponent = DaggerViewModelComponent.builder()
                .repositoryComponent(repositoryComponent)
                .viewModelModule(ViewModelModule(this))
                .build()
    }

    fun getViewModelComponent(): ViewModelComponent {
        return this.viewModelComponent!!
    }
}