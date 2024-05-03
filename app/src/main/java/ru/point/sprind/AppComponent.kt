package ru.point.sprind

import dagger.Component
import ru.point.di.RepositoryModule
import ru.point.domain.usecase.di.UseCaseModule
import ru.point.sprind.presenter.cart.CartFragment
import ru.point.sprind.presenter.favorites.FavoritesFragment
import ru.point.sprind.presenter.morda.MordaFragment
import ru.point.sprind.presenter.product.ProductCardFragment
import ru.point.sprind.presenter.result.ResultFragment

@Component(modules = [RepositoryModule::class, UseCaseModule::class])
interface AppComponent {

    fun inject(fragment: MordaFragment)

    fun inject(fragment: ResultFragment)

    fun inject(fragment: ProductCardFragment)

    fun inject(fragment: CartFragment)

    fun inject(favoritesFragment: FavoritesFragment)
}