package ru.point.sprind.presenter.product.card

import androidx.lifecycle.Lifecycle
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.HttpException
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.AllCharacteristicsVo
import ru.point.domain.entity.view.product.info.CharacteristicDescriptionVo
import ru.point.domain.entity.view.product.info.CharacteristicTitleVo
import ru.point.domain.entity.view.product.info.ProductTitleVo
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.cart.DeleteProductFromCartUseCase
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase
import ru.point.domain.usecase.interfaces.product.GetProductByIdUseCase
import ru.point.sprind.entity.deletage.product.card.AllCharacteristicsDelegate
import ru.point.sprind.entity.deletage.product.card.CharacteristicDelegate
import ru.point.sprind.entity.deletage.product.card.CharacteristicTitleDelegate
import ru.point.sprind.entity.deletage.product.card.NestedRecyclerViewDelegate
import ru.point.sprind.entity.deletage.product.card.ProductCardReviewDelegate
import ru.point.sprind.entity.deletage.product.card.ProductDescriptionDelegate
import ru.point.sprind.entity.deletage.product.card.ProductImageDelegate
import ru.point.sprind.entity.deletage.product.card.ProductTitleDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import ru.point.sprind.presenter.product.card.ProductPresenterAssistedFactory.Companion.ID
import ru.point.sprind.presenter.product.card.ProductPresenterAssistedFactory.Companion.LIFECYCLE

@InjectViewState
class ProductPresenter @AssistedInject constructor(
    @Assisted(ID) private val productId: Long,
    @Assisted(LIFECYCLE) lifecycle: Lifecycle,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val favoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
    private val addProductToCartUseCase: Lazy<AddProductToCartUseCase>,
    private val deleteFromCartUseCase: Lazy<DeleteProductFromCartUseCase>
) : MvpPresenter<ProductCardView>() {

    private val httpManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState::requireAuthorization }
        .addDefaultExceptionHandler { viewState::displaySomethingGoesWrongError }
        .build()

    val delegates = listOf(
        NestedRecyclerViewDelegate(
            delegates = listOf(ProductImageDelegate()),
            useViewPagerEffect = true,
            lifecycle = lifecycle
        ),
        ProductTitleDelegate(::onCheckedFavoriteStateChange),
        ProductDescriptionDelegate(),
        AllCharacteristicsDelegate(::expandCharacteristics),
        CharacteristicDelegate(),
        CharacteristicTitleDelegate(),
        ProductCardReviewDelegate(viewState::openReviews)
    )
    private var productViewObject: List<ViewObject> = emptyList()

    private val compositeDisposable = CompositeDisposable()

    fun init() {
        viewState.displayLoadingScreen(show = true)
        val disposable = getProductByIdUseCase
            .invoke(id = productId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                productViewObject = list
                viewState.displayLoadingScreen(show = false)

                val index = productViewObject.indexOfFirst { vo -> vo is ProductTitleVo }

                if (index != -1) {
                    val vo = productViewObject[index] as ProductTitleVo
                    if (vo.isInCart) viewState.displayProductInCartButtonGroup(show = true)
                    else viewState.displayProductInCartButtonGroup(show = false)
                }

                viewState.setAdapter(productViewObject)
            }, { ex ->
                viewState.displayLoadingScreen(show = false)
                viewState.displayBadConnectionScreen(show = true)
                if (ex is HttpException) httpManager.handle(ex)
            }
            )

        compositeDisposable.add(disposable)
    }

    fun deleteFromCart() {
        val disposable = deleteFromCartUseCase.get().handle(productId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.displayProductInCartButtonGroup(show = false)
            }, { ex ->
                if (ex is HttpException) httpManager.handle(ex)
            })

        compositeDisposable.add(disposable)
    }

    fun addToCart() {
        val disposable = addProductToCartUseCase.get()
            .handle(productId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.displayProductInCartButtonGroup(show = true)
            }, { ex ->
                if (ex is HttpException) httpManager.handle(ex)
            })

        compositeDisposable.add(disposable)
    }

    private fun onCheckedFavoriteStateChange(
        isChecked: Boolean,
        isSuccessfulCallback: (Boolean) -> Unit,
    ) {
        val disposable = favoriteStateUseCase.get()
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

    private fun expandCharacteristics(isExpanded: Boolean) {
        val startIndex = productViewObject.indexOfFirst { view -> view is AllCharacteristicsVo }

        if (!makeSureThatExpandIsNeeded(startIndex, productViewObject)) return

        val characteristic = productViewObject[startIndex] as AllCharacteristicsVo
        if (isExpanded) {
            val nel = characteristic.run {
                productCharacteristicDtos
                    .map { characteristic ->
                        listOf(
                            CharacteristicTitleVo(characteristic.name)
                        ) + characteristic.elements.map { pair ->
                            CharacteristicDescriptionVo(pair.first, pair.second)
                        }
                    }
                    .flatten()
            }
            productViewObject = buildExpandedList(startIndex, nel)
        } else {
            val endIndex =
                productViewObject.indexOfLast { view -> view is CharacteristicDescriptionVo }
            productViewObject = buildNotExpandedList(startIndex, endIndex)
        }

        viewState.setAdapter(productViewObject)
    }

    private fun makeSureThatExpandIsNeeded(index: Int, views: List<ViewObject>): Boolean {
        if (index == -1) return false

        val characteristic = views[index] as? AllCharacteristicsVo ?: return false
        return characteristic.productCharacteristicDtos.isNotEmpty()
    }

    private fun buildExpandedList(
        indexForInsert: Int,
        characteristics: List<ViewObject>,
    ): List<ViewObject> {
        return productViewObject.subList(
            0,
            indexForInsert + 1
        ) + characteristics + productViewObject.subList(indexForInsert + 1, productViewObject.size)
    }

    private fun buildNotExpandedList(
        indexForInsert: Int,
        indexOfEnd: Int,
    ): List<ViewObject> {
        return productViewObject.subList(
            0,
            indexForInsert + 1
        ) + productViewObject.subList(indexOfEnd + 1, productViewObject.size)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}