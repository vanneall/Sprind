package ru.point.sprind.presenter.maps.popup.select

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.map.SelectAddressUseCase
import ru.point.domain.utils.StringFormatter
import javax.inject.Inject

@InjectViewState
class AddressSelectionPresenter @Inject constructor(
    private val selectAddressUseCase: SelectAddressUseCase,
) : MvpPresenter<AddressSelectionView>() {

    val compositeDisposable = CompositeDisposable()

    fun commit(city: String, street: String, house: String, flat: String) {

        if (!StringFormatter.checkStringsNotEmpty(city, street, house, flat)) {
            viewState.displayErrorOnInputLayout(
                city.isEmpty(),
                street.isEmpty(),
                house.isEmpty(),
                flat.isEmpty()
            )
            return
        }

        val disposable = selectAddressUseCase.handle(
            city = city,
            street = street,
            house = house,
            flat = flat
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.closeAddressSelectionStack()
            }, { ex ->
                ex.printStackTrace()
                viewState.displaySomethingGoesWrongError()
            })

        compositeDisposable.add(disposable)
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}