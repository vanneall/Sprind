package ru.point.sprind.di.usecases

import dagger.Module
import dagger.Provides
import ru.point.domain.factory.interfaces.ReviewRequestFactory
import ru.point.domain.repository.ReviewRepository
import ru.point.domain.usecase.implementation.review.AddReviewUseCaseImpl
import ru.point.domain.usecase.implementation.review.GetReviewsByProductIdUseCaseImpl
import ru.point.domain.usecase.interfaces.review.AddReviewUseCase
import ru.point.domain.usecase.interfaces.review.GetReviewsByProductIdUseCase

@Module
class ReviewUseCaseModule {

    @Provides
    fun provideGetReviewsByIdUseCase(repository: ReviewRepository): GetReviewsByProductIdUseCase {
        return GetReviewsByProductIdUseCaseImpl(repository = repository)
    }

    @Provides
    fun provideAddReviewUseCase(
        repository: ReviewRepository,
        factory: ReviewRequestFactory
    ): AddReviewUseCase {
        return AddReviewUseCaseImpl(repository = repository, factory = factory)
    }
}