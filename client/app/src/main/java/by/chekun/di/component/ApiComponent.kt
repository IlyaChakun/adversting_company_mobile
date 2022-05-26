package by.chekun.di.component


import by.chekun.di.module.ApiModule
import by.chekun.di.scope.ApiScope
import by.chekun.repository.server.ServerCommunicator
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class])
interface ApiComponent {
    val serverCommunicator: ServerCommunicator
}
