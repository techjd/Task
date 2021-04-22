package com.techjd.task.model.BadRequest

data class BadRequest(
    val errorCategory: String,
    val errorCode: String,
    val errorDateTime: String,
    val errorDescription: String,
    val errorParameters: List<ErrorParameter>
)