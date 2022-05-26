package by.chekun.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.annotation.StringRes
import by.chekun.App


abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    fun getContext() = getApplication<App>()
    fun getString(@StringRes id: Int): String = getContext().getString(id)
}