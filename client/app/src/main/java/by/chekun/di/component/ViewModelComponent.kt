package by.chekun.di.component


import by.chekun.di.module.ViewModelModule
import by.chekun.di.scope.ViewModelScope
import by.chekun.presentation.activities.add.AddAdvertisementActivity
import by.chekun.presentation.activities.detail.DetailActivity
import by.chekun.presentation.activities.login.LoginActivity
import by.chekun.presentation.activities.login.RegistrationActivity
import by.chekun.presentation.activities.main.MainActivity
import by.chekun.presentation.activities.main.MainAdminActivity
import by.chekun.presentation.activities.user.ProfileActivity
import by.chekun.repository.database.entity.user.RegisterRequest
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [RepositoryComponent::class])
interface ViewModelComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(activity: AddAdvertisementActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: RegistrationActivity)
    fun inject(activity: MainAdminActivity)
    fun inject(activity: ProfileActivity)
}