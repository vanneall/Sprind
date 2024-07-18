package ru.point.sprind.presenter.product.result

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.observable
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import retrofit2.HttpException
import ru.point.domain.entity.view.search.EmptyRequestResultVo
import ru.point.domain.manager.ProductManager
import ru.point.domain.usecase.interfaces.product.GetProductsByNameUseCase
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import ru.point.sprind.entity.deletage.product.request.EmptyRequestResultDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import ru.point.sprind.presenter.product.result.ResultProductFeedPresenter.Factory.Companion.QUERY
import ru.point.sprind.utils.pagerConfig

@OptIn(ExperimentalCoroutinesApi::class)
@InjectViewState
class ResultProductFeedPresenter @AssistedInject constructor(
    @Assisted(QUERY) private val query: String,
    getProductsByNameUseCase: GetProductsByNameUseCase,
    private val productManager: Lazy<ProductManager>
) : MvpPresenter<ResultProductFeedView>() {

    private val httpExceptionManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState::navigateToAuthorization }
        .add404ExceptionHandler { viewState.setAdapter(PagingData.from(listOf(EmptyRequestResultVo()))) }
        .addDefaultExceptionHandler { viewState::showSomethingGoesWrongError }
        .build()

    val viewDelegates = listOf(
        ProductDelegate(
            onClickCard = viewState::navigateToProductCard,
            onBuyClick = ::addProductToCart,
            onFavoriteCheckedChange = ::changeProductInFavoriteState
        ),
        EmptyRequestResultDelegate()
    )

    private val mainCompositeDisposable = CompositeDisposable()

    init {
        viewState.showLoading(show = true)
        val pagingDisposable = Pager(
            config = pagerConfig,
            pagingSourceFactory = { getProductsByNameUseCase.handle(query) }
        ).observable
            .cachedIn(presenterScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> viewState.setAdapter(data) },
                { ex -> handleException(exception = ex) }
            )
        mainCompositeDisposable.add(pagingDisposable)
    }

    fun handleException(exception: Throwable) {
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
        changeFavoriteStateCallback: (Boolean) -> Unit,
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

    @AssistedFactory
    interface Factory {
        fun create(@Assisted(QUERY) request: String): ResultProductFeedPresenter

        companion object {
            const val QUERY = "ru.point.sprind.presenter.product.result.QUERY"
        }
    }
}