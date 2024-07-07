package ru.point.sprind.presenter.favorites

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.observable
import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import retrofit2.HttpException
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase
import ru.point.domain.usecase.interfaces.favorite.GetFavoritesUseCase
import ru.point.sprind.entity.deletage.product.favorites.EmptyFavoritesDelegate
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@InjectViewState
class FavoritePresenter @Inject constructor(
    private val addProductToCartUseCase: Lazy<AddProductToCartUseCase>,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val changeFavoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
) : MvpPresenter<FavoriteView>() {

    private val httpManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState.requireAuthorization() }
        .addDefaultExceptionHandler { viewState.displaySomethingGoesWrongError() }
        .build()

    private val compositeDisposable = CompositeDisposable()

    val delegates = listOf(
        ProductDelegate(
            onClickCard = viewState::openCard,
            onBuyClick = ::onAddProductToCart,
            onFavoriteCheckedChange = ::onCheckedFavoriteStateChange
        ),
        EmptyFavoritesDelegate()
    )

    init {
        val disposable = Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 10,
                maxSize = 45,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { getFavoritesUseCase.handle() }
        ).observable
            .cachedIn(presenterScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                viewState.displayLoadingScreen(show = false)
                viewState.setAdapter(data)
            }, { ex ->
                if (ex is HttpException) httpManager.handle(ex)
                else viewState.displayBadConnectionScreen(show = true)
            })
        compositeDisposable.add(disposable)
    }



    private fun onAddProductToCart(productId: Long) {
        val disposable = addProductToCartUseCase.get().handle(id = productId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { ex ->
                if (ex is HttpException) httpManager.handle(ex)
            })

        compositeDisposable.add(disposable);
    }

    private fun onCheckedFavoriteStateChange(
        productId: Long,
        isChecked: Boolean,
        isSuccessfulCallback: (Boolean) -> Unit,
    ) {
        val disposable = changeFavoriteStateUseCase.get()
            .handle(id = productId, isFavorite = isChecked)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isSuccessfulCallback(true)
            }, { ex ->
                isSuccessfulCallback(false)
                if (ex is HttpException) httpManager.handle(ex)
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}