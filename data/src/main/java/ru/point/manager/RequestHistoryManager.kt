package ru.point.manager

import io.reactivex.rxjava3.core.Single
import ru.point.domain.repository.RequestRepository
import javax.inject.Inject

class RequestHistoryManager @Inject constructor(
    private val requestRepository: RequestRepository,
) {
    fun insert(request: String) {
        requestRepository.insert(request)
    }

    fun getAll(): Single<List<String>> {
        return requestRepository.getAll()
    }
}