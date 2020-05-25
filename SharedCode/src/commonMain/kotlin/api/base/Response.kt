package com.farsidelabs.pacingforynab.api.base

sealed class Response<out T> {
    class Success<out T>(val data: T) : Response<T>()
    data class Error(
        val throwable: Throwable,
        val code: Int? = null
    ) : Response<Nothing>()
}