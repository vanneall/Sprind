package ru.point.sprind.presenter.product.morda

import android.util.Log
import androidx.paging.Pager
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
import ru.point.domain.manager.ProductManager
import ru.point.domain.usecase.interfaces.product.GetMainProductsPageInfoUseCase
import ru.point.domain.usecase.interfaces.product.GetProductsUseCase
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import ru.point.sprind.utils.pagerConfig
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@InjectViewState
class MainProductFeedPresenter @Inject constructor(
    getProductsUseCase: GetProductsUseCase,
    getMainProductsPageInfoUseCase: GetMainProductsPageInfoUseCase,
    private val productManager: Lazy<ProductManager>
) : MvpPresenter<MainProductFeedView>() {

    private val httpExceptionManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState.navigateToAuthorization() }
        .addDefaultExceptionHandler { viewState.showSomethingGoesWrongError() }
        .build()

    val viewDelegates = listOf(
        ProductDelegate(
            onClickCard = viewState::navigateToProductCard,
            onBuyClick = ::addProductToCart,
            onFavoriteCheckedChange = ::changeProductInFavoriteState
        )
    )

    private val mainCompositeDisposable = CompositeDisposable()

    init {
        viewState.showLoading(show = true)
        val pageInfoDisposable = getMainProductsPageInfoUseCase.handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { info -> viewState.setAddress(info.addressVo.address) },
                { ex -> handleException(exception = ex) }
            )

        val pagingDisposable = Pager(
            config = pagerConfig,
            pagingSourceFactory = getProductsUseCase::handle
        ).observable
            .cachedIn(presenterScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { pagingData -> viewState.setAdapter(pagingData) },
                { ex -> handleException(exception = ex) }
            )

        mainCompositeDisposable.add(pagingDisposable)
        mainCompositeDisposable.add(pageInfoDisposable)
    }

    private fun handleException(exception: Throwable) {
        if (exception is HttpException) {
            httpExceptionManager.handle(exception = exception)
        } else {
            viewState.showBadConnection(show = true)
            Log.e("Exception", exception.stackTraceToString())
        }
    }

    private fun addProductToCart(productId: Long) {
        productManager.get().addToCart(
            productId = productId,
            onError = ::handleException
        )
    }

    private fun changeProductInFavoriteState(
        productId: Long,
        isInFavoriteNow: Boolean,
        changeFavoriteStateCallback: (isSuccessful: Boolean) -> Unit,
    ) {
        productManager.get().changeProductInFavoriteState(
            productId = productId,
            isInFavorite = isInFavoriteNow,
            onComplete = { changeFavoriteStateCallback(true) },
            onError = { ex ->
                changeFavoriteStateCallback(false)
                handleException(exception = ex)
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mainCompositeDisposable.clear()
        productManager.get().clearActiveRequests()
    }
}