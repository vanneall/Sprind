package ru.point.repository.retrofit

import okhttp3.Interceptor
import okhttp3.Response
import ru.point.storage.SettingsManager
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val manager: SettingsManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val path = chain.request().url().url().path
        val requireAuthorizationHeader = authorizationRequiredEndpoints
            .any { endpoint -> path.endsWith(endpoint) }

        if (requireAuthorizationHeader) {
            val handledRequest = chain.request()
                .newBuilder()
                .addHeader("Authorization", manager.token.value)
                .build()

            return chain.proceed(handledRequest)
        }

        return chain.proceed(chain.request())
    }

    private val authorizationRequiredEndpoints = listOf(
        "/cart",
        "/cart/add",
        "/feed",
        "/favorites"
    )
}