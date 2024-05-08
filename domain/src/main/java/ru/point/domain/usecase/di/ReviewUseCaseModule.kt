package ru.point.domain.usecase.di

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.ReviewRepository
import ru.point.domain.usecase.implementation.AddReviewUseCaseImpl
import ru.point.domain.usecase.implementation.GetReviewsByProductIdUseCaseImpl
import ru.point.domain.usecase.interfaces.AddReviewUseCase
import ru.point.domain.usecase.interfaces.GetReviewsByProductIdUseCase

@Module
class ReviewUseCaseModule {

    @Provides
    fun provideGetReviewsByIdUseCase(repository: ReviewRepository): GetReviewsByProductIdUseCase {
        return GetReviewsByProductIdUseCaseImpl(repository = repository)
    }

    @Provides
    fun provideAddReviewUseCase(repository: ReviewRepository): AddReviewUseCase {
        return AddReviewUseCaseImpl(repository = repository)
    }
}