package by.chekun.di.module


import by.chekun.di.scope.RepositoryScope
import by.chekun.repository.AppRepository
import by.chekun.repository.database.AppDatabase

import by.chekun.repository.server.ServerCommunicator
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @RepositoryScope
    @Provides
    internal fun providesRepository(communicator: ServerCommunicator, database: AppDatabase): AppRepository {
        return AppRepository(communicator, database)
    }
}

