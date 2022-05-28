package by.chekun.di.module

import android.app.Application
import by.chekun.App
import by.chekun.di.scope.ViewModelScope
import by.chekun.domain.AddAdvertisementViewModel
import by.chekun.domain.AllAdvertisementsViewModel
import by.chekun.domain.SingleAdvertisementViewModel
import by.chekun.repository.AppRepository

import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(app: App) {

    internal var app: Application = app

    @ViewModelScope
    @Provides
    internal fun providesAllCarsViewModel(repository: AppRepository): AllAdvertisementsViewModel {
        return AllAdvertisementsViewModel(app, repository)
    }

    @ViewModelScope
    @Provides
    internal fun providesSingleCarViewModel(repository: AppRepository): SingleAdvertisementViewModel {
        return SingleAdvertisementViewModel(app, repository)
    }

    @ViewModelScope
    @Provides
    internal fun providesAddCarViewModel(repository: AppRepository): AddAdvertisementViewModel {
        return AddAdvertisementViewModel(app, repository)
    }
}