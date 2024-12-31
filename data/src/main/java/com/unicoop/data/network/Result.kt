package com.unicoop.data.network

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    data class Loading(val isLoading: Boolean) : Result<Nothing>()
}

inline fun <reified T> Result<T>.doIfSuccess(data: (T) -> Unit) {
    if (this is Result.Success) data(this.data)
}

inline fun <reified T> Result<T>.doIfError(error: (Exception) -> Unit) {
    if (this is Result.Error) error(this.exception)
}

fun <T> Result<T>.result(onLoading: () -> Unit, onSuccess: () -> Unit): T? {
    return when (val result = this) {
        is Result.Success -> {
            onSuccess()
            result.data
        }

        is Result.Error -> {
            throw result.exception
        }

        is Result.Loading -> {
            onLoading()
            null
        }
    }
}

fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> {
    return when (this) {
        is Result.Success -> Result.Success(transform(data))
        is Result.Error -> this
        is Result.Loading -> this
    }
}



