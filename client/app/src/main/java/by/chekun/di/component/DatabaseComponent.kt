package by.chekun.di.component

import by.chekun.di.module.DatabaseModule
import by.chekun.repository.database.AppDatabase
import dagger.Component


@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
}
