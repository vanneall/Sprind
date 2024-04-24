package ru.point.sprind

typealias Request = String

interface RequestManager {

    fun getRequestsHistory(): List<ru.point.domain.entity.Request>

    fun addRequest(request: Request)

}