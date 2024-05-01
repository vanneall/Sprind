package ru.point.repository.retrofit


import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import ru.point.domain.entity.dto.FeedProductDto

const val toke1n =
    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkZWZveHl3ZWV4QGdtYWlsLmNvbSIsImV4cCI6MTcxNDY4MTA4MSwiaWF0IjoxNzE0NTk0NjgxLCJhdXRob3JpdHkiOlsiUk9MRV9VU0VSIl19.GNs5wE5u5O7BHE7HNkXqfwuJ22qDjWPzOjkSyjLoXhg"

interface CartApi {

    @GET("cart")
    fun getProductsFromCart(@Header("Authorization") token: String = toke1n): Observable<List<FeedProductDto>>
}