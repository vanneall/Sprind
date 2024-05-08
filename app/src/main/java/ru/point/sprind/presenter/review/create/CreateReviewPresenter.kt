package ru.point.sprind.presenter.review.create

import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.AddReviewUseCase
import ru.point.sprind.presenter.review.create.CreateReviewPresenterFactory.Companion.ID

@InjectViewState
class CreateReviewPresenter @AssistedInject constructor(
    @Assisted(ID)
    private val productId: Long,
    private val addReviewUseCase: Lazy<AddReviewUseCase>,
) : MvpPresenter<CreateReviewView>() {

    private val compositeDisposable = CompositeDisposable()

    fun addReview(rating: String, description: String) {
        val disposable = addReviewUseCase.get().handle(productId = productId, rating.toFloat(), description = description)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {it.printStackTrace()})

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}