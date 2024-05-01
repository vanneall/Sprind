package ru.point.repository.retrofit


import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import ru.point.domain.entity.dto.FeedProductDto

const val toke1n =
    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkZWZveHl3ZWV4QGdtYWlsLmNvbSIsImV4cCI6MTcxNDU4MTEzOCwiaWF0IjoxNzE0NDk0NzM4LCJhdXRob3JpdHkiOlsiUk9MRV9VU0VSIl19.Kzl3g-NOSFY6MLvdbO9sASJ6Vou2s_uVJe2ZM-gS5n4"

interface CartApi {

    @GET("cart")
    fun getProductsFromCart(@Header("Authorization") token: String = toke1n): Observable<List<FeedProductDto>>
}