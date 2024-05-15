package ru.point.domain.usecase.interfaces.map

import io.reactivex.rxjava3.core.Completable

interface SelectAddressUseCase {
    fun handle(city: String, street: String, house: String, flat: String): Completable
}