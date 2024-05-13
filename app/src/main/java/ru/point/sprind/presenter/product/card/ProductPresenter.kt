package ru.point.sprind.presenter.product.card

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.AllCharacteristicsVo
import ru.point.domain.entity.view.product.info.CharacteristicDescriptionVo
import ru.point.domain.entity.view.product.info.CharacteristicTitleVo
import ru.point.domain.mapper.implementations.ProductDtoToListViewMapperImpl
import ru.point.domain.usecase.interfaces.product.GetProductByIdUseCase
import ru.point.sprind.entity.deletage.product.card.AllCharacteristicsDelegate
import ru.point.sprind.entity.deletage.product.card.CharacteristicDelegate
import ru.point.sprind.entity.deletage.product.card.CharacteristicTitleDelegate
import ru.point.sprind.entity.deletage.product.card.ProductCardReviewDelegate
import ru.point.sprind.entity.deletage.product.card.ProductDescriptionDelegate
import ru.point.sprind.entity.deletage.product.card.ProductImageDelegate
import ru.point.sprind.entity.deletage.product.card.ProductTitleDelegate
import ru.point.sprind.presenter.product.card.ProductPresenterAssistedFactory.Companion.ID

@InjectViewState
class ProductPresenter @AssistedInject constructor(
    @Assisted(ID) private val productId: Long,
    private val getProductByIdUseCase: GetProductByIdUseCase,
) : MvpPresenter<ProductCardView>() {

    val delegates = listOf(
        ProductImageDelegate(),
        ProductTitleDelegate(),
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
            .invoke(id = productId, ProductDtoToListViewMapperImpl())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                productViewObject = list
                viewState.displayLoadingScreen(show = false)

                viewState.setAdapter(productViewObject)
            }, { ex ->
                ex.printStackTrace()
                viewState.displayLoadingScreen(show = false)
                viewState.displayBadConnectionScreen(show = true)
            }
            )

        compositeDisposable.add(disposable)
    }

    private fun expandCharacteristics(isExpanded: Boolean) {
        val startIndex = productViewObject.indexOfFirst { view -> view is AllCharacteristicsVo }

        if (startIndex == -1) return
        val characteristic = productViewObject[startIndex] as? AllCharacteristicsVo ?: return

        if (isExpanded) {
            val nel = characteristic.run {
                productCharacteristicDtos.map { characteristic ->
                    listOf(
                        CharacteristicTitleVo(characteristic.name)
                    ) + characteristic.elements.map { pair ->
                        CharacteristicDescriptionVo(pair.first, pair.second)
                    }
                }.flatten()
            }

            productViewObject = buildExpandedList(startIndex, nel)
        } else {
            val endIndex =
                productViewObject.indexOfLast { view -> view is CharacteristicDescriptionVo }
            productViewObject = buildNotExpandedList(startIndex, endIndex)
        }



        characteristic.let {
            viewState.setAdapter(productViewObject)
        }
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