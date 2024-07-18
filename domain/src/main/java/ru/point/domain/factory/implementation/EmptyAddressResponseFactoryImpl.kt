package ru.point.domain.factory.implementation

import ru.point.domain.entity.response.address.AddressInfoResponse
import ru.point.domain.factory.interfaces.EmptyAddressResponseFactory

class EmptyAddressResponseFactoryImpl : EmptyAddressResponseFactory {
    override fun create(): AddressInfoResponse {
        return AddressInfoResponse(
            city = null,
            street = null,
            house = null,
            flat = null
        )
    }
}