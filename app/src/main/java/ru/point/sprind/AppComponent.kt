package ru.point.sprind

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.point.di.InterceptorModule
import ru.point.di.ManagerModule
import ru.point.di.RepositoryModule
import ru.point.di.RetrofitModule
import ru.point.domain.usecase.di.UseCaseModule
import ru.point.sprind.presenter.auth.authorization.AuthorizationFragment
import ru.point.sprind.presenter.auth.registration.credentials.CredentialsFragment
import ru.point.sprind.presenter.auth.registration.password.RegistrationFragment
import ru.point.sprind.presenter.cart.CartFragment
import ru.point.sprind.presenter.favorites.FavoritesFragment
import ru.point.sprind.presenter.morda.MordaFragment
import ru.point.sprind.presenter.product.ProductCardFragment
import ru.point.sprind.presenter.result.ResultFragment
import ru.point.sprind.presenter.review.all.AllReviewsFragment
import ru.point.sprind.presenter.review.create.CreateReviewFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class, UseCaseModule::class,
        ManagerModule::class, InterceptorModule::class,
        RetrofitModule::class]
)
interface AppComponent {

    fun inject(application: SprindApplication)

    fun inject(fragment: MordaFragment)

    fun inject(fragment: ResultFragment)

    fun inject(fragment: ProductCardFragment)

    fun inject(fragment: CartFragment)

    fun inject(fragment: FavoritesFragment)

    fun inject(fragment: AuthorizationFragment)

    fun inject(fragment: CredentialsFragment)

    fun inject(fragment: RegistrationFragment)

    fun inject(fragment: AllReviewsFragment)

    fun inject(fragment: CreateReviewFragment)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}