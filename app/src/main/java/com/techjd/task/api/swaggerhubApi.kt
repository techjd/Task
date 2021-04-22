package com.techjd.task.api

import com.techjd.task.model.SuccessObject.SuccessObject
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.Call
import retrofit2.http.Body

interface swaggerhubApi {

    @Multipart
    @POST("user/reportissue")
    fun reportIssue(
        @Part title: MultipartBody.Part,
        @Part description: MultipartBody.Part
    ): Call<SuccessObject>

    @POST("user/contact")
    fun contactUs(
        @Body userDetails: String
    ): Call<SuccessObject>
}