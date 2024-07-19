package ru.point.sprind.presenter.category

import android.util.Log
import androidx.paging.rxjava3.cachedIn
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import retrofit2.HttpException
import ru.point.domain.manager.ProductManager
import ru.point.domain.usecase.interfaces.category.GetCategoryProductsPageUseCase
import ru.point.sprind.adapters.decorators.AvailableCategoriesItemDecorator
import ru.point.sprind.entity.deletage.product.card.NestedRecyclerViewDelegate
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import ru.point.sprind.entity.deletage.shop.ShopDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import ru.point.sprind.presenter.category.CategoryPresenter.Factory.Companion.ID

@InjectViewState
class CategoryPresenter @AssistedInject constructor(
    @Assisted(ID) categoryId: Long,
    getCategoryProductsPageUseCase: GetCategoryProductsPageUseCase,
    private val productManager: Lazy<ProductManager>
) : MvpPresenter<CategoryView>() {

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
        ),
        NestedRecyclerViewDelegate(
            delegates = listOf(ShopDelegate(viewState::navigateToShopScreen)),
            itemDecoration = AvailableCategoriesItemDecorator()
        )
    )

    private val mainCompositeDisposable = CompositeDisposable()

    init {
        val pagingDisposable = getCategoryProductsPageUseCase.handle(categoryId = categoryId)
            .cachedIn(presenterScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { pagingData -> viewState.setAdapter(pagingData) },
                { ex -> handleException(exception = ex) }
            )

        mainCompositeDisposable.add(pagingDisposable)
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
    ) {
        productManager.get().changeProductInFavoriteState(
            productId = productId,
            isInFavorite = isInFavoriteNow,
            onComplete = viewState::refresh,
            onError = { ex -> handleException(exception = ex) }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mainCompositeDisposable.clear()
        productManager.get().clearActiveRequests()
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted(ID) categoryId: Long): CategoryPresenter

        companion object {
            const val ID = "ru.point.sprind.presenter.category.ID"
        }
    }
}