package ru.point.sprind.entity.manager

import android.util.Log
import retrofit2.HttpException

@JvmInline
value class Code(val code: Int)

class HttpExceptionStatusManager private constructor() {

    private var default: (() -> Unit) = {
        Log.e("HttpExceptionStatusManager", "Handler for this exception not implemented")
    }

    private val handlerMap = mutableMapOf(Code(DEFAULT) to default)

    fun handle(exception: HttpException) {
        val code = Code(exception.code())
        if (handlerMap.containsKey(code)) handlerMap[code]?.invoke()
        else handlerMap[Code(DEFAULT)]?.invoke()

        exception.printStackTrace()
    }

    private companion object {
        const val DEFAULT = -1
    }

    class Builder {

        private val manager: HttpExceptionStatusManager = HttpExceptionStatusManager()

        fun add400ExceptionHandler(handler: () -> Unit): Builder {
            manager.handlerMap[Code(400)] = handler
            return this
        }

        fun add403ExceptionHandler(handler: () -> Unit): Builder {
            manager.handlerMap[Code(403)] = handler
            return this
        }

        fun add404ExceptionHandler(handler: () -> Unit): Builder {
            manager.handlerMap[Code(404)] = handler
            return this
        }

        fun addDefaultExceptionHandler(handler: () -> Unit): Builder {
            manager.handlerMap[Code(DEFAULT)] = handler
            return this
        }

        fun build(): HttpExceptionStatusManager {
            return manager
        }
    }
}