package com.techjd.task.model.UnauthorisedRequest

data class UnAuthorisedRequest(
    val errorCategory: String,
    val errorCode: String,
    val errorDateTime: String,
    val errorDescription: String,
    val errorParameters: List<ErrorParameter>
)