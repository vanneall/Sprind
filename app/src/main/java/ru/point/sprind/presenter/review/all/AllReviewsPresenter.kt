package ru.point.sprind.presenter.review.all

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.GetReviewsByProductIdUseCase
import ru.point.sprind.entity.deletage.product.review.ReviewDelegate
import ru.point.sprind.presenter.review.all.AllReviewsPresenterFactory.Companion.ID

@InjectViewState
class AllReviewsPresenter @AssistedInject constructor(
    @Assisted(ID) id: Long,
    private val getReviewsByProductIdUseCase: GetReviewsByProductIdUseCase,
) : MvpPresenter<AllReviewsView>() {

    private val compositeDisposable = CompositeDisposable()

    val delegates = listOf(
        ReviewDelegate()
    )

    init {
        val disposable = getReviewsByProductIdUseCase.handle(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.setProductAdapter(it)
            }, { it.printStackTrace() })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}