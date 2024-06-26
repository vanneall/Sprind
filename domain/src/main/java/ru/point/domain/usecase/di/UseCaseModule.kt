package ru.point.domain.usecase.di

import dagger.Module

@Module(
    includes = [
        ProfileUseCaseModule::class, CartUseCaseModule::class,
        FavoriteUseCaseModule::class, ProductUseCaseModule::class,
        ReviewUseCaseModule::class, MapUseCaseModule::class]
)
class UseCaseModule