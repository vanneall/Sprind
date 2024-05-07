package ru.point.sprind

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.point.di.ManagerModule
import ru.point.di.RepositoryModule
import ru.point.domain.usecase.di.UseCaseModule
import ru.point.sprind.presenter.auth.authorization.AuthorizationFragment
import ru.point.sprind.presenter.cart.CartFragment
import ru.point.sprind.presenter.favorites.FavoritesFragment
import ru.point.sprind.presenter.morda.MordaFragment
import ru.point.sprind.presenter.product.ProductCardFragment
import ru.point.sprind.presenter.result.ResultFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, UseCaseModule::class, ManagerModule::class])
interface AppComponent {

    fun inject(fragment: MordaFragment)

    fun inject(fragment: ResultFragment)

    fun inject(fragment: ProductCardFragment)

    fun inject(fragment: CartFragment)

    fun inject(favoritesFragment: FavoritesFragment)

    fun inject(fragment: AuthorizationFragment)

    fun inject(application: SprindApplication)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}