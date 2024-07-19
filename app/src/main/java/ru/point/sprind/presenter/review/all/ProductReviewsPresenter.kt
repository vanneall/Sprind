package ru.point.sprind.presenter.review.all

import android.util.Log
import androidx.paging.rxjava3.cachedIn
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import retrofit2.HttpException
import ru.point.domain.usecase.interfaces.review.GetReviewsByProductIdUseCase
import ru.point.sprind.entity.deletage.product.review.ReviewDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import ru.point.sprind.presenter.review.all.ProductReviewsPresenter.Factory.Companion.ID

@InjectViewState
class ProductReviewsPresenter @AssistedInject constructor(
    @Assisted(ID)
    private val id: Long,
    getReviewsByProductIdUseCase: GetReviewsByProductIdUseCase,
) : MvpPresenter<ProductReviewsView>() {

    private val httpExceptionManager = HttpExceptionStatusManager
        .Builder()
        .addDefaultExceptionHandler { viewState::showSomethingGoesWrongError }
        .build()

    private val compositeDisposable = CompositeDisposable()

    val delegates = listOf(
        ReviewDelegate()
    )

    init {
        viewState.showLoading(show = true)
        val disposable = getReviewsByProductIdUseCase.handle(id)
            .cachedIn(presenterScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.setAdapter(list)
            }, { ex ->
                handleException(exception = ex)
            })

        compositeDisposable.add(disposable)
    }

    private fun handleException(exception: Throwable) {
        if (exception is HttpException) {
            httpExceptionManager.handle(exception = exception)
        } else {
            viewState.showBadConnection(show = true)
            Log.e("Exception", exception.stackTraceToString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted(ID) id: Long): ProductReviewsPresenter

        companion object {
            const val ID = "ID"
        }
    }
}