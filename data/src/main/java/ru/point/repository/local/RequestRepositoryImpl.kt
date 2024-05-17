package ru.point.repository.local

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.repository.RequestRepository
import ru.point.room.RequestDao
import ru.point.room.RequestEntity
import javax.inject.Inject

class RequestRepositoryImpl @Inject constructor(
    private val dao: RequestDao,
) : RequestRepository {

    val compositeDisposable = CompositeDisposable()

    override fun insert(request: String) {
        val requestEntity = RequestEntity(request = request)
        val disposable = dao.insertRequestEntity(requestEntity)
            .subscribeOn(Schedulers.io())
            .subscribe({

            }, { ex ->
                ex.printStackTrace()
            })

        compositeDisposable.addAll(disposable)
    }

    override fun getAll(): Single<List<String>> {
        return dao.getAll()
            .subscribeOn(Schedulers.io())
            .map { list -> list.map { request -> request.request } }
    }
}