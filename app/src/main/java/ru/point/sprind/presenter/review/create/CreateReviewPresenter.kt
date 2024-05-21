package ru.point.sprind.presenter.review.create

import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.review.AddReviewUseCase
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import ru.point.sprind.presenter.review.create.CreateReviewPresenterFactory.Companion.ID

@InjectViewState
class CreateReviewPresenter @AssistedInject constructor(
    @Assisted(ID)
    private val productId: Long,
    private val addReviewUseCase: Lazy<AddReviewUseCase>,
) : MvpPresenter<CreateReviewView>() {

    private val httpManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState.requireAuthorization() }
        .addDefaultExceptionHandler { viewState.displaySomethingGoesWrongError() }
        .build()

    private val compositeDisposable = CompositeDisposable()

    fun addReview(rating: String, description: String, advantage: String?, disadvantage: String?) {
        val disposable = addReviewUseCase.get().handle(
            productId = productId,
            rating = rating.toFloat(),
            description = description,
            advantage = advantage,
            disadvantage = disadvantage
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.exit()
            }, { ex ->
                ex.printStackTrace()
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}