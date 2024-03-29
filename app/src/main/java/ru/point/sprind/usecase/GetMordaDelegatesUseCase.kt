package ru.point.sprind.usecase

import ru.point.sprind.entity.deletage.Delegate

interface GetMordaDelegatesUseCase {
    fun handle(): List<Delegate>
}