package com.github.bgrebennikov.devbuff.data

import com.github.bgrebennikov.devbuff.data.local.explore.Status

data class ApiResponse<out T>(
    val status : Status,
    val data: T?,
    val message : String?
) {
    companion object{

        fun <T> success(data: T) = ApiResponse(
            status = Status.SUCCESS,
            data = data,
            message = null
        )

        fun <T> error(data: T?, message: String) = ApiResponse(
            status = Status.ERROR,
            data = data,
            message = message,
        )

        fun <T> loading(data: T?) = ApiResponse(
            status = Status.LOADING,
            data = data,
            message = null
        )

    }
}