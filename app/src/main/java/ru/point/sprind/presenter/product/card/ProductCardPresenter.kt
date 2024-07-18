package ru.point.sprind.presenter.product.card

import android.util.Log
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.HttpException
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.ProductCharacteristicsVo
import ru.point.domain.entity.view.product.info.CharacteristicDescriptionVo
import ru.point.domain.entity.view.product.info.CharacteristicTitleVo
import ru.point.domain.entity.view.product.info.ProductTitleVo
import ru.point.domain.manager.ProductManager
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
import ru.point.sprind.presenter.product.card.ProductCardPresenter.Factory.Companion.ID
import ru.point.sprind.utils.firstIndex


@InjectViewState
class ProductCardPresenter @AssistedInject constructor(
    @Assisted(ID) private val productId: Long,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val productManager: Lazy<ProductManager>
) : MvpPresenter<ProductCardViewDefault>() {

    private val httpExceptionManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState.navigateToAuthorization() }
        .addDefaultExceptionHandler { viewState.showSomethingGoesWrongError() }
        .build()

    val viewDelegates = listOf(
        NestedRecyclerViewDelegate(
            delegates = listOf(ProductImageDelegate()),
            useViewPagerEffect = true
        ),
        ProductTitleDelegate(::onCheckedFavoriteStateChange),
        ProductDescriptionDelegate(),
        AllCharacteristicsDelegate(::expandCharacteristics, ::collapseCharacteristics),
        CharacteristicDelegate(),
        CharacteristicTitleDelegate(),
        ProductCardReviewDelegate(viewState::navigateToReviews)
    )
    private var productsVo: List<ViewObject> = emptyList()

    private val mainCompositeDisposable = CompositeDisposable()

    fun init() {
        viewState.showLoading(show = true)
        val productInfoDisposable = getProductByIdUseCase.invoke(id = productId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                productsVo = list

                val index = productsVo.indexOfFirst { vo -> vo is ProductTitleVo }
                if (index != -1) {
                    val vo = productsVo[index] as ProductTitleVo
                    if (vo.isInCart) viewState.displayProductInCartButtonGroup(show = true)
                    else viewState.displayProductInCartButtonGroup(show = false)
                }

                viewState.setAdapter(productsVo)
            }, { ex -> handleException(exception = ex) }
            )

        mainCompositeDisposable.add(productInfoDisposable)
    }

    private fun handleException(exception: Throwable) {
        if (exception is HttpException) {
            httpExceptionManager.handle(exception = exception)
        } else {
            viewState.showBadConnection(show = true)
            Log.e("Exception", exception.stackTraceToString())
        }
    }

    fun removeFromCart() {
        productManager.get().removeFromCart(
            productId = productId,
            onComplete = { viewState.displayProductInCartButtonGroup(show = false) },
            onError = ::handleException
        )
    }

    fun addToCart() {
        productManager.get().addToCart(
            productId = productId,
            onComplete = { viewState.displayProductInCartButtonGroup(show = true) },
            onError = ::handleException
        )
    }

    private fun onCheckedFavoriteStateChange(
        isChecked: Boolean,
        isSuccessfulCallback: (Boolean) -> Unit,
    ) {
        productManager.get().changeProductInFavoriteState(
            productId = productId,
            isInFavorite = isChecked,
            onComplete = { isSuccessfulCallback(true) },
            onError = { ex ->
                isSuccessfulCallback(false)
                handleException(exception = ex)
            }
        )
    }

    private fun expandCharacteristics() {
        val charactersSectionStart = productsVo
            .indexOfFirst { vo -> vo is ProductCharacteristicsVo }
            .takeIf { index -> index != -1 }
            ?: return

        val productCharacteristicsVo = productsVo[charactersSectionStart] as ProductCharacteristicsVo

        val characteristics = productCharacteristicsVo.characteristicsResponse.map { section ->
            val sectionTitle = CharacteristicTitleVo(section.sectionTitle)

            val sectionDescriptions = section.descriptions.map { description ->
                CharacteristicDescriptionVo(description.first, description.second)
            }

            listOf(sectionTitle) + sectionDescriptions
        }.flatten()

        productsVo = buildExpandedList(
            characteristics = characteristics,
            characteristicsStart = charactersSectionStart + 1
        )

        viewState.setAdapter(productsVo)
    }

    private fun buildExpandedList(
        characteristics: List<ViewObject>,
        characteristicsStart: Int
    ): List<ViewObject> {
        val beforeCharacteristicsList = productsVo.subList(
            fromIndex = productsVo.firstIndex,
            toIndex = characteristicsStart
        )

        val afterCharacteristicsList = productsVo.subList(
            fromIndex = characteristicsStart,
            toIndex = productsVo.lastIndex + 1
        )

        return beforeCharacteristicsList + characteristics + afterCharacteristicsList
    }

    private fun collapseCharacteristics() {
        val charactersSectionStart = productsVo
            .indexOfFirst { vo -> vo is ProductCharacteristicsVo }
            .takeIf { index -> index != -1 }
            ?: return

        val charactersSectionEnd = productsVo
            .indexOfLast { vo -> vo is CharacteristicDescriptionVo }
            .takeIf { index -> index != -1 }
            ?: return

        productsVo = buildCollapsedList(charactersSectionStart + 1, charactersSectionEnd + 1)

        viewState.setAdapter(productsVo)
    }

    private fun buildCollapsedList(
        characteristicsStart: Int,
        characteristicsEnd: Int
    ): List<ViewObject> {
        val beforeCharacteristicsList = productsVo.subList(
            fromIndex = productsVo.firstIndex,
            toIndex = characteristicsStart
        )

        val afterCharacteristicsList = productsVo.subList(
            fromIndex = characteristicsEnd,
            toIndex = productsVo.lastIndex + 1
        )

        return beforeCharacteristicsList + afterCharacteristicsList
    }

    override fun onDestroy() {
        super.onDestroy()
        mainCompositeDisposable.clear()
        productManager.get().clearActiveRequests()
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted(ID) productId: Long): ProductCardPresenter

        companion object {
            const val ID = "ru.point.sprind.presenter.product.card.ID"
        }
    }
}