package ru.point.domain.usecase.implementation.map

import io.reactivex.rxjava3.core.Completable
import ru.point.domain.entity.utils.AddressInfoResponse
import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.interfaces.map.SelectAddressUseCase

class SelectAddressUseCaseImpl(
    private val repository: UserRepository,
) : SelectAddressUseCase {
    override fun handle(city: String, street: String, house: String, flat: String): Completable {
        return repository.setNewAddress(
            AddressInfoResponse(
                city = city,
                street = street,
                house = house,
                flat = flat
            )
        )
    }
}