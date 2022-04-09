package com.example.foodorder.utilise

sealed class ServerResponse<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T) : ServerResponse<T>(data = data)
    class Error<T>(error: String) : ServerResponse<T>(error = error)
}
