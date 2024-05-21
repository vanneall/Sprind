package ru.point.sprind.entity.manager

import android.util.Log
import retrofit2.HttpException

class HttpExceptionStatusManager private constructor() {

    private var handle400: (() -> Unit)? = null

    private var handle403: (() -> Unit)? = null

    private var handle404: (() -> Unit)? = null

    private var default: (() -> Unit) = {
        Log.e("HttpExceptionStatusManager", "Handler for this exception not implemented")
    }

    fun handle(exception: HttpException) {
        when {
            exception.code() == 404 && handle404 != null -> handle404?.invoke()
            exception.code() == 400 && handle400 != null -> handle400?.invoke()
            exception.code() == 403 && handle403 != null -> handle403?.invoke()

            else -> {
                default()
                exception.printStackTrace()
            }
        }
    }

    class Builder {

        private val manager: HttpExceptionStatusManager = HttpExceptionStatusManager()

        fun add400ExceptionHandler(handler: () -> Unit): Builder {
            manager.handle400 = handler
            return this
        }

        fun add403ExceptionHandler(handler: () -> Unit): Builder {
            manager.handle403 = handler
            return this
        }

        fun add404ExceptionHandler(handler: () -> Unit): Builder {
            manager.handle404 = handler
            return this
        }

        fun addDefaultExceptionHandler(handler: () -> Unit): Builder {
            manager.default = handler
            return this
        }

        fun build(): HttpExceptionStatusManager {
            return manager
        }
    }
}