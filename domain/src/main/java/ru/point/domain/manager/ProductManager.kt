package ru.point.domain.manager

import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.cart.RemoveProductFromCartUseCase
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase
import javax.inject.Inject

class ProductManager @Inject constructor(
    private val addProductToCartUseCase: Lazy<AddProductToCartUseCase>,
    private val deleteFromCartUseCase: Lazy<RemoveProductFromCartUseCase>,
    private val changeFavoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
) {
    private val mainCompositeDisposable = CompositeDisposable()

    fun addToCart(
        productId: Long,
        onComplete: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null
    ) {
        val addToCartDisposable = addProductToCartUseCase.get()
            .handle(id = productId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onComplete?.invoke() },
                { ex -> onError?.invoke(ex) }
            )

        mainCompositeDisposable.add(addToCartDisposable);
    }

    fun removeFromCart(
        productId: Long,
        onComplete: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null
    ) {
        val deleteProductDisposable = deleteFromCartUseCase.get()
            .handle(id = productId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onComplete?.invoke() },
                { ex -> onError?.invoke(ex) }
            )

        mainCompositeDisposable.add(deleteProductDisposable)
    }

    fun changeProductInFavoriteState(
        productId: Long,
        isInFavorite: Boolean,
        onComplete: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null
    ) {
        val changeFavoriteDisposable = changeFavoriteStateUseCase.get()
            .handle(id = productId, isFavorite = isInFavorite)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onComplete?.invoke() },
                { ex -> onError?.invoke(ex) }
            )

        mainCompositeDisposable.add(changeFavoriteDisposable)
    }

    fun clearActiveRequests() {
        mainCompositeDisposable.clear()
    }
}