package ru.point.retrofit.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.point.manager.SettingsManager
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val manager: SettingsManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val path = chain.request().url().url().path
        var requireAuthorizationHeader = authorizationRequiredEndpoints
            .any { endpoint -> path.endsWith(endpoint) }

        //if (path.endsWith("/reviews") && chain.request().url().)

        if (requireAuthorizationHeader && manager.token.value.isNotEmpty()) {
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
        "/favorites",
        "/reviews"
    )
}