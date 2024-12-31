package com.unicoop.data.network

import java.io.IOException

sealed class NetworkResponse<out T : Any, out U : Any> {
    /**
     * Success response with body
     */
    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    /**
     * Failure response with body
     */
    data class ApiError<U : Any>(val body: U, val code: Int) : NetworkResponse<Nothing, U>()

    /**
     * Network error
     */
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
}

suspend fun <T : Any, U : Any> NetworkResponse<T, U>.response(
    onSuccess: suspend(T) -> Unit = {},
    onApiFail: (U) -> Unit = {},
    onError: (Throwable) -> Unit = {}
) {
    when (this) {
        is NetworkResponse.Success -> {
            onSuccess(this.body)
        }
        is NetworkResponse.ApiError -> {
            onApiFail(this.body)
        }

        is NetworkResponse.NetworkError -> onError(this.error)
        is NetworkResponse.UnknownError -> onError(this.error ?: Exception("Unknown Error"))
    }
}