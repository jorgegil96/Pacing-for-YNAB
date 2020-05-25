package com.farsidelabs.pacingforynab.feature.base

sealed class Result<out T> {
    class Success<out T>(val data: T) : Result<T>()
    data class Error(
        val throwable: Throwable,
        val code: Int? = null
    ) : Result<Nothing>()
}