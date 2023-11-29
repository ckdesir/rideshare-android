package com.cornellappdev.scoop.ui.viewmodel

sealed class ApiResponse<out T : Any> {
    object Pending : ApiResponse<Nothing>()
    object Error : ApiResponse<Nothing>()
    class Success<out T : Any>(val data: T) : ApiResponse<T>()
}