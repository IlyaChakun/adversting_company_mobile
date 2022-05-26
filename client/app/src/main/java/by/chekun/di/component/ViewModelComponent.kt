package by.chekun.di.component


import by.chekun.di.module.ViewModelModule
import by.chekun.di.scope.ViewModelScope
import by.chekun.presentation.activities.add.AddCarActivity
import by.chekun.presentation.activities.detail.DetailActivity
import by.chekun.presentation.activities.main.MainActivity
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [RepositoryComponent::class])
interface ViewModelComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(activity: AddCarActivity)
}