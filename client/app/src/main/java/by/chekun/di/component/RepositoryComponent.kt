package by.chekun.di.component

import by.chekun.di.module.RepositoryModule
import by.chekun.di.scope.RepositoryScope
import by.chekun.repository.AppRepository
import dagger.Component

@RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val repository: AppRepository
}