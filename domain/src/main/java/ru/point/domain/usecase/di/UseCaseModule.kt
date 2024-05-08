package ru.point.domain.usecase.di

import dagger.Module

@Module(
    includes = [
        AuthUseCaseModule::class, CartUseCaseModule::class,
        FavoriteUseCaseModule::class, ProductUseCaseModule::class,
        ReviewUseCaseModule::class]
)
class UseCaseModule