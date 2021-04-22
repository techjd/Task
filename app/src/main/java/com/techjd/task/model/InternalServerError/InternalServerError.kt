package com.techjd.task.model.InternalServerError

data class InternalServerError(
    val errorCategory: String,
    val errorCode: String,
    val errorDateTime: String,
    val errorDescription: String,
    val errorParameters: List<ErrorParameter>
)