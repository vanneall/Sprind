package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.point.domain.entity.dto.review.CreatedReviewDto
import ru.point.domain.entity.dto.review.ReviewDto

interface ReviewApi {
    @GET("product/{id}/reviews")
    fun getReviewsById(@Path("id") id: Long): Observable<List<ReviewDto>>

    @POST("product/{id}/reviews")
    fun postReview(@Path("id") id: Long, @Body dto: CreatedReviewDto): Completable
}
