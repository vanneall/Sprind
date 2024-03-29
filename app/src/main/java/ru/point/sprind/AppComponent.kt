package ru.point.sprind

import dagger.Component
import ru.point.RepositoryModule
import ru.point.domain.usecase.di.UseCaseModule
import ru.point.sprind.di.DelegateModule
import ru.point.sprind.presenter.morda.MordaFragment

@Component(modules = [RepositoryModule::class, UseCaseModule::class, DelegateModule::class])
interface AppComponent {

    fun inject(fragment: MordaFragment)
}