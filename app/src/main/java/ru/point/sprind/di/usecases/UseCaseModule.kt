package ru.point.sprind.di.usecases

import dagger.Module

@Module(
    includes = [
        ProfileUseCaseModule::class, CartUseCaseModule::class,
        FavoriteUseCaseModule::class, ProductUseCaseModule::class,
        ReviewUseCaseModule::class, MapUseCaseModule::class]
)
class UseCaseModule