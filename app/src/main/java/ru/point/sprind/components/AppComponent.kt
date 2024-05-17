package ru.point.sprind.components

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.point.di.InterceptorModule
import ru.point.di.ManagerModule
import ru.point.di.RepositoryModule
import ru.point.di.RetrofitModule
import ru.point.di.RoomModule
import ru.point.domain.factory.di.FactoryModule
import ru.point.domain.usecase.di.UseCaseModule
import ru.point.sprind.di.MapUtilsModule
import ru.point.sprind.presenter.auth.authorization.AuthorizationFragment
import ru.point.sprind.presenter.auth.registration.credentials.CredentialsFragment
import ru.point.sprind.presenter.auth.registration.password.RegistrationFragment
import ru.point.sprind.presenter.cart.CartFragment
import ru.point.sprind.presenter.favorites.FavoritesFragment
import ru.point.sprind.presenter.maps.map.MapFragment
import ru.point.sprind.presenter.maps.popup.select.AddressSelectionDialogFragment
import ru.point.sprind.presenter.product.card.ProductCardFragment
import ru.point.sprind.presenter.product.morda.MordaFragment
import ru.point.sprind.presenter.product.result.ResultFragment
import ru.point.sprind.presenter.profile.main.ProfileFragment
import ru.point.sprind.presenter.review.all.AllReviewsFragment
import ru.point.sprind.presenter.review.create.CreateReviewFragment
import ru.point.sprind.presenter.search.SearchFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        UseCaseModule::class, FactoryModule::class,
        RepositoryModule::class, RetrofitModule::class,
        InterceptorModule::class, ManagerModule::class,
        MapUtilsModule::class, RoomModule::class
    ]
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

    fun inject(fragment: ProfileFragment)

    fun inject(fragment: MapFragment)

    fun inject(fragment: AddressSelectionDialogFragment)

    fun inject(fragment: SearchFragment)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}