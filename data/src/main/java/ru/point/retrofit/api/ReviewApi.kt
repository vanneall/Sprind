package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.point.domain.entity.request.review.CreateReviewRequest
import ru.point.domain.entity.response.review.ReviewResponse

interface ReviewApi {
    @GET("product/{id}/reviews")
    fun getProductsReview(
        @Path("id") productId: Long,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Single<List<ReviewResponse>>

    @POST("product/{id}/reviews")
    fun createReview(@Path("id") id: Long, @Body requestDto: CreateReviewRequest): Completable
}
