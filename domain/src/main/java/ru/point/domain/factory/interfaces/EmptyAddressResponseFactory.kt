package ru.point.domain.factory.interfaces

import ru.point.domain.entity.response.address.AddressInfoResponse

interface EmptyAddressResponseFactory {
    fun create(): AddressInfoResponse
}