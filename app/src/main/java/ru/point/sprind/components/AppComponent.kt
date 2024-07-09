package ru.point.sprind.components

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.point.sprind.di.modules.InterceptorModule
import ru.point.sprind.di.modules.ManagerModule
import ru.point.sprind.di.modules.RepositoryModule
import ru.point.sprind.di.modules.RetrofitModule
import ru.point.room.di.RoomModule
import ru.point.sprind.di.factories.FactoryModule
import ru.point.sprind.di.usecases.UseCaseModule
import ru.point.sprind.di.MapUtilsModule
import ru.point.sprind.presenter.auth.authorization.AuthorizationFragment
import ru.point.sprind.presenter.auth.registration.credentials.CredentialsFragment
import ru.point.sprind.presenter.auth.registration.password.RegistrationFragment
import ru.point.sprind.presenter.cart.CartFragment
import ru.point.sprind.presenter.favorites.FavoritesFragment
import ru.point.sprind.presenter.maps.map.MapFragment
import ru.point.sprind.presenter.maps.popup.select.AddressSelectionDialogFragment
import ru.point.sprind.presenter.product.card.ProductCardFragment
import ru.point.sprind.presenter.product.morda.MainProductFeedFragment
import ru.point.sprind.presenter.product.result.ResultProductFeedFragment
import ru.point.sprind.presenter.profile.main.ProfileFragment
import ru.point.sprind.presenter.profile.orders.OrdersFragment
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

    fun inject(activity: MainActivity)

    fun inject(fragment: MainProductFeedFragment)

    fun inject(fragment: ResultProductFeedFragment)

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

    fun inject(fragment: OrdersFragment)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}